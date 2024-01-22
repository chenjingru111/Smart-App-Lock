package com.heaven.smartapplock.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.heaven.smartapplock.OnCircleCompleteListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class CircleView extends View {
    private OnCircleCompleteListener listener;


    private List<Point> circlePoints; // 圆圈的中心点坐标
    private List<Point> linePoints; // 线条的起始点和终止点坐标
    private Paint circlePaint; // 圆圈的画笔
    private Paint linePaint; // 线条的画笔
    private String a="0";

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr,OnCircleCompleteListener listener) {
        super(context, attrs, defStyleAttr);
        this.listener = listener;
        init();
    }

    private void init() {
        circlePoints = new ArrayList<>();
        linePoints = new ArrayList<>();

        // 初始化圆圈的画笔
        circlePaint = new Paint();
        circlePaint.setColor(Color.GRAY);
        circlePaint.setStyle(Paint.Style.FILL);

        // 初始化线条的画笔
        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // 计算九个圆圈的中心点坐标
        int radius = Math.min(w, h) / 9;
        int spacing = (int) (radius * 1.5); // 圆圈之间的间隔大小

        int startX = (w - spacing * 2 * 3) / 2;
        int startY = (h - spacing * 2 * 3) / 2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int centerX = startX + spacing + j * spacing * 2;
                int centerY = startY + spacing + i * spacing * 2;
                circlePoints.add(new Point(centerX, centerY));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制圆圈
        for (int i = 0; i < circlePoints.size(); i++) {
            Point point = circlePoints.get(i);
            if (a.contains(String.valueOf(i + 1))) {
                circlePaint.setColor(Color.BLUE);  // 如果a包含当前圆圈的编号，则设置为绿色
            } else {
                circlePaint.setColor(Color.GRAY);  // 否则设置为红色
            }
            canvas.drawCircle(point.x, point.y, 50, circlePaint);
        }
        // 绘制线条
        Path path = new Path();
        for (int i = 0; i < linePoints.size(); i += 2) {
            Point startPoint = linePoints.get(i);
            Point endPoint = linePoints.get(i + 1);
            path.moveTo(startPoint.x, startPoint.y);
            path.lineTo(endPoint.x, endPoint.y);
        }
        canvas.drawPath(path, linePaint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 清空线条的起始点和终止点坐标
                linePoints.clear();
                break;
            case MotionEvent.ACTION_MOVE:
                // 获取当前手指的坐标
                int x = (int) event.getX();
                int y = (int) event.getY();

                // 判断手指是否在圆圈内
                for (Point point : circlePoints) {
                    if (Math.pow(x - point.x, 2) + Math.pow(y - point.y, 2) <= Math.pow(50, 2)) {
                        // 添加线条的起始点和终止点坐标
                        linePoints.add(new Point(point.x, point.y));
                        linePoints.add(new Point(x, y));
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                // 输出经过了哪些圆圈
                StringBuilder result = new StringBuilder();
                for (Point point : linePoints) {
                    for (int i = 0; i < circlePoints.size(); i++) {
                        Point circlePoint = circlePoints.get(i);
                        if (point.x == circlePoint.x && point.y == circlePoint.y) {
                            result.append(i + 1).append(" ");
                            break;
                        }
                    }
                }


                String resultString = result.toString();
                Set<Character> charSet = new HashSet<>();
                // 将字符串中的每个字符添加到Set集合中
                for (int i = 0; i < resultString.length(); i++) {
                    charSet.add(resultString.charAt(i));
                }
                // 将Set集合转换回字符串
                StringBuilder sb = new StringBuilder();
                for (Character c : charSet) {
                    sb.append(c);
                }
                a = sb.toString();
                System.out.println("经过了圆圈：" + a);

                // 通过接口返回结果
                if (listener != null) {
                    listener.onCircleComplete(a);
                }
                break;
        }

        // 重绘View
        invalidate();
        return true;
    }
    public void setOnCircleCompleteListener(OnCircleCompleteListener listener) {
        this.listener = listener;
    }

}
