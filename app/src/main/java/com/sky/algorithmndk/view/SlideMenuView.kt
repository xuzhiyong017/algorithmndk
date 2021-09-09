package com.sky.algorithmndk.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.Scroller


/**
 * @author: xuzhiyong
 * @date: 21-1-6  上午11:07
 * @Email: 18971269648@163.com
 * @description:
 */
class SlideMenuView :ViewGroup{

    lateinit var menuView: View
    lateinit var contentView: View
    lateinit var mScroller: Scroller

    var mScreenWidth:Int = 0;
    var mScreenHeight:Int = 0;
    var mMenuRightPadding:Int = 0;
    var mMenuWidth:Int = 0;
    var mContentWidth:Int = 0;
    var isOpen:Boolean = false;

    constructor(context: Context?) : this(context,null) {}
    constructor(context: Context?, attrs: AttributeSet?) : this(context,attrs,0){
    }

    constructor(context: Context?,
                attrs: AttributeSet? = null,
                defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        val metrics = DisplayMetrics()

        val wm =
            context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        wm.defaultDisplay.getMetrics(metrics)

        //获取屏幕的宽和高


        //获取屏幕的宽和高
        mScreenWidth = metrics.widthPixels

        mScreenHeight = metrics.heightPixels

        //设置Menu距离屏幕右侧的距离，convertToDp是将代码中的100转换成100dp


        //设置Menu距离屏幕右侧的距离，convertToDp是将代码中的100转换成100dp
        mMenuRightPadding = 200

        mScroller = Scroller(context,LinearInterpolator())
    }

    override fun computeScroll() {
        Log.d(SlideMenuView::class.java.simpleName,"computeScroll")
       if(mScroller.computeScrollOffset()){
           Log.d(SlideMenuView::class.java.simpleName,"computeScroll scrollX="+scrollX)
           scrollTo(mScroller.currX,mScroller.currY)
           slidingMode3()
           invalidate()
       }
    }

    private fun slidingMode3() {
        var scale = Math.abs(scrollX.toFloat()) / mMenuWidth.toFloat()
        menuView.setTranslationX(mMenuWidth + scrollX - mMenuWidth / 2 * (1.0f - scale))
        menuView.setScaleX(0.7f + 0.3f * scale)
        menuView.setScaleY(0.7f + 0.3f * scale)
        menuView.setAlpha(scale)
        contentView.setScaleX(1 - 0.3f * scale)
        contentView.setPivotX(0F)
        contentView.setScaleY(1.0f - 0.3f * scale)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        menuView = getChildAt(0);
        contentView = getChildAt(1);

        mMenuWidth = mScreenWidth - mMenuRightPadding
        menuView.layoutParams.width = mMenuWidth

        mContentWidth = mScreenWidth;
        contentView.layoutParams.width = mContentWidth

        measureChild(menuView,widthMeasureSpec,heightMeasureSpec)
        measureChild(contentView,widthMeasureSpec,heightMeasureSpec)

        Log.d("SlideMenuView","onMeasure")
        setMeasuredDimension(mMenuWidth + mContentWidth,mScreenHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        menuView.layout(-mMenuWidth,0,0,mScreenHeight);
        contentView.layout(0,0,mScreenWidth,mScreenHeight);
        Log.d("SlideMenuView","onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("SlideMenuView","onDraw")
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        Log.d("SlideMenuView","draw")
    }

    var lastX = 0.0f;

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
            }
            MotionEvent.ACTION_MOVE -> {
                var dx = event.x - lastX;
                slidingMode3()
                Log.d(SlideMenuView::class.java.simpleName,"scrollX="+scrollX+" dx="+dx)
                if(dx < 0){
                    if(scrollX + Math.abs(dx) >= 0){
                        scrollTo(0,0)
                    }else{
                        scrollBy(-dx.toInt(),0)
                    }
                }else{

                    if(scrollX - dx <= -mMenuWidth){
                        scrollTo(-mMenuWidth,0)
                    }else{
                        scrollBy(-dx.toInt(),0)
                    }
                }
                lastX = event.x;
            }
            MotionEvent.ACTION_UP ->{
                if(scrollX < -mMenuWidth /2){
                    mScroller.startScroll(scrollX,0,-mMenuWidth-scrollX,0,300);
                    isOpen = true
                    invalidate()
                }else{
                    mScroller.startScroll(scrollX,0,-scrollX,0,300)
                    isOpen = false
                    invalidate()

                }
            }
        }
        return true

    }
}