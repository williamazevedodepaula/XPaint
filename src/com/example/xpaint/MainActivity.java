package com.example.xpaint;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
    private ImageButton icPencil;
    private ImageButton icRectangle;
    private ImageButton icCircle;
    private ImageButton icLine;
    private tela canvas;
    
    private ImageButton btPreto;
    private ImageButton btCinza;
    private ImageButton btCinza2;
    private ImageButton btVinho;
    private ImageButton btVermelho;
    private ImageButton btRosa;
    private ImageButton btLaranja;
    private ImageButton btAmarelho;
    private ImageButton btVerde;
    private ImageButton btVerde2;
    private ImageButton btAzul;
    private ImageButton btAzul2;
    private ImageButton btBranco;
    
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		icPencil = (ImageButton) findViewById(R.id.icPencil);
		icPencil.setOnClickListener(new onPencilClick());
		icRectangle = (ImageButton) findViewById(R.id.icRectangle);
		icRectangle.setOnClickListener(new onRectClick());
		icCircle = (ImageButton) findViewById(R.id.icCircle);
		icCircle.setOnClickListener(new onCircleClick());
		icLine = (ImageButton) findViewById(R.id.icLine);
		icLine.setOnClickListener(new onLineClick());
		
		canvas = (tela) findViewById(R.id.canvas);
		
		btPreto = (ImageButton)findViewById(R.id._01Black);
		btCinza = (ImageButton)findViewById(R.id._02Gray);
		btCinza2 = (ImageButton)findViewById(R.id._03Gray2);
		btVinho = (ImageButton)findViewById(R.id._05Vinho);
		btVermelho = (ImageButton)findViewById(R.id._06Vermelho);
		btRosa = (ImageButton)findViewById(R.id._07Rosa);
		btLaranja = (ImageButton)findViewById(R.id._08Laranja);
		btAmarelho = (ImageButton)findViewById(R.id._09Amarelo);
		btVerde = (ImageButton)findViewById(R.id._10Verde);
		btVerde2 = (ImageButton)findViewById(R.id._11VerdeClaro);
		btAzul = (ImageButton)findViewById(R.id._12Azul);
		btAzul2 = (ImageButton)findViewById(R.id._13AzulClaro);
		btBranco = (ImageButton)findViewById(R.id._14Branco);
		
		btPreto.setOnClickListener(new btcorClick());
		btCinza.setOnClickListener(new btcorClick());
		btCinza2.setOnClickListener(new btcorClick());
		btVinho.setOnClickListener(new btcorClick());
		btVermelho.setOnClickListener(new btcorClick());
		btRosa.setOnClickListener(new btcorClick());
		btLaranja.setOnClickListener(new btcorClick());
		btAmarelho.setOnClickListener(new btcorClick());
		btVerde.setOnClickListener(new btcorClick());
		btVerde2.setOnClickListener(new btcorClick());
		btAzul.setOnClickListener(new btcorClick());
		btAzul2.setOnClickListener(new btcorClick());
		btBranco.setOnClickListener(new btcorClick());
		
	}
	
	public class btcorClick implements OnClickListener{

		
		@Override
		public void onClick(View v) {
			//canvas.status.SetColor(Color.BLACK);
			canvas.status.SetColor(Color.parseColor(v.getTag().toString()));
			
			
			btPreto.setImageResource(R.drawable.ic_rect);
			btCinza.setImageResource(R.drawable.ic_rect);
			btCinza2.setImageResource(R.drawable.ic_rect);
			btVinho .setImageResource(R.drawable.ic_rect);
			btVermelho.setImageResource(R.drawable.ic_rect);
			btRosa .setImageResource(R.drawable.ic_rect);
			btLaranja .setImageResource(R.drawable.ic_rect);
			btAmarelho.setImageResource(R.drawable.ic_rect);
			btVerde .setImageResource(R.drawable.ic_rect);
			btVerde2.setImageResource(R.drawable.ic_rect);
			btAzul .setImageResource(R.drawable.ic_rect);
			btAzul2.setImageResource(R.drawable.ic_rect);
			btBranco.setImageResource(R.drawable.ic_rect);
			
			ImageButton bt = (ImageButton) findViewById(v.getId());
			bt.setImageResource(R.drawable.ic_rec_selected);
		}
		
	}
	

	
	
	
	public class onPencilClick implements OnClickListener{	

		@Override
		public void onClick(View v) {
			ToolSelect(icPencil);
			canvas.status.SelectTool(tela.Tool.toPencil);
		}		
	}
	public class onCircleClick implements OnClickListener{	
		
		@Override
		public void onClick(View v) {
			ToolSelect(icCircle);
			canvas.status.SelectTool(tela.Tool.toCircle);
		}			
	}
	public class onRectClick implements OnClickListener{	
		@Override
		public void onClick(View v) {
			ToolSelect(icRectangle);
			canvas.status.SelectTool(tela.Tool.toRectangle);
		}		
	}
	public class onLineClick implements OnClickListener{	
		@Override
		public void onClick(View v) {
			ToolSelect(icLine);
			canvas.status.SelectTool(tela.Tool.toLine);
		}		
	}
	@SuppressLint("ResourceAsColor")
	public void ToolSelect(ImageButton botao){
		botao.setBackgroundResource(android.R.color.darker_gray);
		if(botao != icPencil){
		   icPencil.setBackgroundResource(android.R.color.transparent);
		   
		}
		if(botao != icRectangle)
			icRectangle.setBackgroundResource(android.R.color.transparent);
		if(botao != icCircle)
			icCircle.setBackgroundResource(android.R.color.transparent);
		if(botao != icLine)
			icLine.setBackgroundResource(android.R.color.transparent);
	}
	
	
	

}
