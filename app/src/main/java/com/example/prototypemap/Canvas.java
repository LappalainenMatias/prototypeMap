package com.example.prototypemap;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class Canvas extends View {

    private String TAG = "Canvas: ";
    HashMap<Pair<Integer,Integer>,Pair<Integer,Integer>> board;
    Paint paint;
    Boolean first = true;
    public Canvas(Context context) {
        super(context);
        paint = new Paint();
        board = new HashMap<>();

    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.blueUI));
        paint.setStrokeWidth(10);
        Log.i(TAG, "onDraw: " + canvas.getWidth() + "," + canvas.getHeight()+ "  " + 0.0%0.5);
        int canvasHeigth = canvas.getHeight()-40;
        int canvasWidth = canvas.getWidth()-40;
        for(int x = 0; x < 11; x += 1){
            for(int y = 0; y < 11; y += 1){
                if(x%5==0.0&&y%5==0.0){
                    paint.setStrokeWidth(20);
                }
                int calculateX = Math.round(canvasWidth*x/10+20);
                int calculateY = Math.round(canvasHeigth*y/10+20);
                board.put(new Pair<Integer,Integer>(x,y),new Pair<Integer,Integer>(calculateX,calculateY));
                canvas.drawPoint(calculateX,calculateY,paint);
                paint.setStrokeWidth(10);
            }
        }
        if(first) {
            Path wallpath = new Path();
            wallpath.reset(); // only needed when reusing this path for a new build
            wallpath.moveTo(board.get(new Pair<Integer, Integer>(1, 2)).first, board.get(new Pair<Integer, Integer>(1, 2)).second); // used for first point
            wallpath.lineTo(board.get(new Pair<Integer, Integer>(1, 7)).first, board.get(new Pair<Integer, Integer>(1, 7)).second);
            wallpath.lineTo(board.get(new Pair<Integer, Integer>(5, 7)).first, board.get(new Pair<Integer, Integer>(5, 7)).second);
            paint.setColor(getResources().getColor(R.color.transparentGreen));
            canvas.drawPath(wallpath, paint);
            wallpath = new Path();
            wallpath.reset(); // only needed when reusing this path for a new build
            wallpath.moveTo(board.get(new Pair<Integer, Integer>(1, 1)).first, board.get(new Pair<Integer, Integer>(1, 1)).second); // used for first point
            wallpath.lineTo(board.get(new Pair<Integer, Integer>(1, 7)).first, board.get(new Pair<Integer, Integer>(1, 7)).second);
            wallpath.lineTo(board.get(new Pair<Integer, Integer>(5, 7)).first, board.get(new Pair<Integer, Integer>(5, 7)).second);
            paint.setColor(getResources().getColor(R.color.transparentRed));
            canvas.drawPath(wallpath, paint);
            first = false;
        }
        super.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(getContext(),"Coordinates: " + event.getX() + ", " + event.getY(),Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }
}
