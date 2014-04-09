package com.example.xpaint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class tela extends View{

	private ImageButton icCircle;
	
	Status status;
	FigureList figuras;
	DrawCoordinates coordenadas;
	List<point> pointList;
	public enum Tool {toPencil,toCircle,toRectangle,toLine};
	
	
	
	public tela(Context context){
		super(context);
		figuras = new FigureList();
		status = new Status(); 
		coordenadas = new DrawCoordinates();
		icCircle = (ImageButton)findViewById(R.id.icCircle); 
		pointList = new ArrayList<point>();
	}
	public tela(Context context, AttributeSet ats){
		super(context, ats);
		
		figuras = new FigureList();
		status  = new Status();
		coordenadas = new DrawCoordinates();
		icCircle = (ImageButton)findViewById(R.id.icPencil);
		pointList = new ArrayList<point>();
	}

	@Override
	protected void onDraw(Canvas canvas){
		
		super.onDraw(canvas);
		
		
		status.setCanvas(canvas);
		status.getPaint().setColor(Color.WHITE);
		canvas.drawPaint(status.getPaint());
		status.getPaint().setColor(status.GetColor());
				
		
		
		status.setCanvas(canvas);
		if(figuras.count()>0){
		
			figuras.top().draw(canvas);
			
		}
	
	}
	@Override
	public void draw(Canvas canvas) {	
		super.draw(canvas);
		status.getPaint().setColor(Color.WHITE);
		canvas.drawPaint(status.getPaint());
		status.getPaint().setColor(status.GetColor());
		
		for(int i = 0;i< figuras.count();i++){
			
			figuras.getItem(i).draw(canvas);
				
			
		}
		
		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		Canvas canvas = status.getCanvas();			
		status.SetColor(status.GetColor());
		Figure figura;
		
		//figuras.add(new Circle(100,50,20,status.getColor(),status.getColor(),status.getPaint()));
		invalidate();
		
	    if(event.getAction()== MotionEvent.ACTION_DOWN){
		   coordenadas.Xini = event.getX();
		   coordenadas.Yini = event.getY();	
		   if(status.getTool()==Tool.toPencil){
			   pointList.clear();
		   }
	    }
	    if(status.getTool()==Tool.toPencil){
			   pointList.add(new point(event.getX(),event.getY()));
		}
	    if(status.getTool()==Tool.toCircle)
	       figura = new Circle(coordenadas.Xini,coordenadas.Yini,Math.abs(coordenadas.Xini-event.getX()),status.GetColor(),status.GetColor(),status.getPaint());
	    else
	    if(status.getTool()==Tool.toRectangle)
	 	       figura = new Rectangle(coordenadas.Xini,coordenadas.Yini,event.getX(),event.getY(),status.GetColor(),status.GetColor(),status.getPaint());
	 	else
	 	if(status.getTool()==Tool.toLine)
		 	   figura = new Line(coordenadas.Xini,coordenadas.Yini,event.getX(),event.getY(),status.GetColor(),status.GetColor(),status.getPaint());
		else			    
	    if(status.getTool()==Tool.toPencil)
		       figura = new PencilDraw(pointList,status.GetColor(),status.getPaint());
		else			    
		    figura=null;
		switch(event.getAction()){
		
			case MotionEvent.ACTION_DOWN:				
				//figura = new Circle(coordenadas.Xini,coordenadas.Yini,0,status.getColor(),status.getColor(),status.getPaint());
				figuras.add(figura );
				break;
			case MotionEvent.ACTION_UP:
				
				
				break;
				
			case MotionEvent.ACTION_MOVE:
				
				figuras.removeItem(figuras.count()-1);
				figuras.add(figura);
				break;
		}
		coordenadas.Xend = event.getX();
		coordenadas.Yend = event.getY();
		invalidate();
		
		
		
		//figuras.add(new Circle(event.getX(),event.getY(),20,status.getColor(),status.getColor(),status.getPaint()));
		
		
		
		return true;		
	
	}
	
	
	
	public class Status{
		private Paint pincel;
		private Paint pincelFundo;
		private Tool  tool;
		private Canvas canvas;
		private int color;
		
		public Status(){
			pincel = new Paint();
			pincel.setColor(Color.BLACK);
			color = Color.BLACK;
			pincelFundo = new Paint();
			//pincelFundo.setColor(Color.WHITE);
			tool = Tool.toPencil;
			canvas = new Canvas();
			canvas.drawPaint(pincel);
		}  
		
		public void SetColor(int color){
			pincel.setColor(color);
			this.color = color;
		}
		public void SetColor(int color, int BackColor){
			pincel.setColor(color);
			this.color = color;
			pincelFundo.setColor(BackColor);
		}
		public void SetBackColor(int color){
			pincelFundo.setColor(color);
		}
		public void SelectTool(Tool tool){
			this.tool = tool;
		}
		public void setPaint(Paint paint){
			pincel = paint;
		}
		public void serBackPaint(Paint paint){
			pincelFundo = paint;
		}
		public void setCanvas(Canvas canvas){
			this.canvas = canvas;
		}
		
		
		public int GetColor(){
			return this.color;
		}
		public int GetBackColor(){
			return pincelFundo.getColor();
		}
		public Tool getTool(){
			return tool;
		}
		public Paint getPaint(){
			return pincel;
		}
		public Paint getBackPaint(){
			return pincelFundo;
		}
		public Canvas getCanvas(){
			return this.canvas;
		}
		
		
	}
	public class Figure{
		float Xaxis;
		float Yaxis;
		int color;
		int backColor;
		Paint paint;
		
		public Figure(float x, float y, int cor, int corFundo, Paint paint){
			Xaxis = x;
			Yaxis = y;
			color = cor;
			backColor = corFundo;
			this.paint = paint; 
		}
		public void draw(Canvas canvas){
			
		}
	}
	public class Rectangle extends Figure{
		
		float XaxisEnd;
		float YaxisEnd;
		public Rectangle(float Xini, float Yini, float Xfin, float Yfin, int cor, int corFundo, Paint paint) {
			super(Xini, Yini, cor, corFundo,paint);
			XaxisEnd = Xfin;
			YaxisEnd = Yfin;
		}
		@Override
		public void draw(Canvas canvas){
			paint.setColor(color);
			canvas.drawRect(Xaxis, Yaxis, XaxisEnd, YaxisEnd, paint);
		}
		
	}
	public class Line extends Figure{
		public Line(float Xini, float Yini, float Xfin, float Yfin, int cor, int corFundo, Paint paint) {
			super(Xini, Yini, cor, corFundo,paint);
			XaxisEnd = Xfin;
			YaxisEnd = Yfin;
		}
		float XaxisEnd;
		float YaxisEnd;
		@Override
		public void draw(Canvas canvas){
			paint.setColor(color);
			canvas.drawLine(Xaxis, Yaxis, XaxisEnd, YaxisEnd, paint);
		}

	}
	public class Circle extends Figure{
		float raio;
		
		public Circle(float x, float y, float raio, int cor, int corFundo, Paint paint) {
			super(x, y, cor, corFundo,paint);
			this.raio = raio; 
		}
		@Override
		public void draw(Canvas canvas){
			paint.setColor(color);
			canvas.drawCircle(Xaxis, Yaxis, raio, paint);
		}

		
		
	}
	public class PencilDraw extends Figure{
		List<point> pointList;
		
				
		
		public PencilDraw(List<point> pointList,int cor, Paint paint) {
			super(0, 0, cor, cor,paint);
			this.pointList = new ArrayList<point>();
			this.pointList.addAll(pointList);
		}
		@Override
		public void draw(Canvas canvas){
			paint.setColor(color);
			for(int i=0;i<this.pointList.size()-1;i++){
				
				canvas.drawLine(pointList.get(i).x, pointList.get(i).y,pointList.get(i+1).x, pointList.get(i+1).y, paint);
				//canvas.drawPoint(pointList.get(i).x, pointList.get(i).y, paint);
				
			}
		}

		
		
	}
	public class point{
		float x;
		float y;
		public point(float x, float y){
			this.x = x;
			this.y = y;
		}
	}
	public class FigureList{
		private int count;
		private List<Figure> list;
		
		public FigureList(){
			count = 0;
			list = new ArrayList<Figure>();
		}
		
		public int count(){
			return count;
		}
		public int add(Figure fig){
			list.add(fig);
			count ++;
			return count;
		}
		public Figure top(){
			return list.get(count-1);
		}
		public Figure botton(){
			return list.get(0);
		}
		public Figure getItem(int position){
			if((position < count)&&(position>-1)){
			   return list.get(position);
			}
			return null;
		}
		public int removeItem(int position){
			if((position < count)&&(position>-1)){
				   list.remove(position);
				   count--;
				   return count;
				}
				return -1;
		}
	}
	
	public class DrawCoordinates{
		public float Xini;
		public float Yini;
		public float Xend;
		public float Yend;
		
		public DrawCoordinates(){
			Xini = 0;
			Xend = 0;
			Yini = 0;
			Yend = 0;
		}
	}
	
	public ImageButton SelectedButton(){
		if(status.getTool()==Tool.toCircle){
		   return icCircle;
		}else
			return null;
		
	}
}
