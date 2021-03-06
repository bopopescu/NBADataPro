package presentation.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

import presentation.common.WaveLabel.WaveAction;

public class MultiWaveLabel extends JLabel implements MouseListener, MouseMotionListener{
	
	private int iniLeftTime = 50;
	
	Timer wakeUpTimer;
	Timer paintTimer;
	private Point currentMousePosition;
	
	private boolean hasWave = false;
	private boolean exited = false;
	private int exitedAlpha = 150;
	
	private ArrayList<Point> sourcePoints;
	private ArrayList<Integer> paintTimes;
	private ArrayList<Integer> paintRadius;
	
	//--------------------------------
	

	protected int alpha = 150;
	protected Point sourcePoint;
	protected int dr;
	protected Timer timer;
	public boolean released = false;
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 400, 400);
		f.setLayout(null);
		
		MultiWaveLabel l = new MultiWaveLabel();
		l.setBounds(50, 50, 300, 300);
		f.add(l);
		
		f.setVisible(true);
	}

	public MultiWaveLabel(){
		initialise();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if(hasWave && sourcePoints.size()>1){
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			for(int i=0;i<sourcePoints.size();i++){
				int leftTime = paintTimes.get(i);
				int r = paintRadius.get(i) + 1;
				Point point = sourcePoints.get(i);
				
				if(leftTime == 0){
					clearWave(i);
				}else{
					paintTimes.set(i, leftTime-1);
					paintRadius.set(i, r);
				}
				
				if(exited){

					g2d.setStroke(new BasicStroke((float) (0.4*(iniLeftTime - leftTime+1))));
					exitedAlpha *= 0.9999;
					g2d.setColor(new Color(150, 150, 200, exitedAlpha));
					g2d.drawOval(point.x - r+1, point.y - r+1, 2*r+2, 2*r+2);
//					System.out.println(exitedAlpha);
				}else{
					g2d.setStroke(new BasicStroke((float) (0.2*(iniLeftTime - leftTime))));
					g2d.setColor(new Color(150, 150, 200, (int)(0.7*255*leftTime)/iniLeftTime));
					g2d.drawOval(point.x - r, point.y - r, 2*r, 2*r);
				}
			}
		}
		
		if(released ){

			if(dr<this.getWidth() || dr<this.getHeight()){
				dr+=20;
				int x = sourcePoint.x - dr;
				int y = sourcePoint.y - dr;
				Graphics2D g2d = (Graphics2D) g;
				Color c = g2d.getColor();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(new Color(50, 50, 90, 30));
				g2d.fillOval(x, y, 2*dr, 2*dr);
				g2d.fillOval(x, y, 2*dr+4, 2*dr+4);
				g2d.fillOval(x, y, 2*dr+8, 2*dr+8);
				g2d.fillOval(x-2, y-2, 2*dr+4, 2*dr+4);
				g2d.setColor(c);
				
//				System.out.println("wave");
			}else{
				if(timer != null){
					timer.stop();
				}
				dr = 0;
			}
			
		}
		
	}
	
	private void clearAll(){
		sourcePoints.clear();
		paintRadius.clear();
		paintTimes.clear();
	}
	
	private void clearWave(int index){
		sourcePoints.remove(index);
		paintRadius.remove(index);
		paintTimes.remove(index);
	}
	
	private void initialise(){
		sourcePoints = new ArrayList<Point>();
		paintTimes = new ArrayList<Integer>();
		paintRadius = new ArrayList<Integer>();
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private void setWakeUpTimer(){
		wakeUpTimer = new Timer(70, new WakeUpAction());
		wakeUpTimer.start();
	}
	
	private void closeWakeUpTimer(){
		wakeUpTimer.stop();
	}
	
	private void setPaintTimer(){
		paintTimer = new Timer(20, new PaintAction());
		paintTimer.start();
	}
	
	private void closePaintTimer(){
		if(paintTimer != null)
		paintTimer.stop();
	}
	
	private void processExit(){
		for(int i=0;i<paintTimes.size();i++){
			paintTimes.set(i, iniLeftTime);
		}
	}
	
	private void makeWave(){
		if(timer != null){
			timer.stop();
		}
		timer = new Timer(30, new WaveAction());
		timer.start();
	}
	
	class WakeUpAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!sourcePoints.isEmpty() 
					&& sourcePoints.get(sourcePoints.size()-1).equals(currentMousePosition)){
				//鼠标位置没变
//				hasChanged = false;
			}else{
//				hasChanged = true;
				sourcePoints.add(currentMousePosition);
				paintTimes.add(iniLeftTime);
				paintRadius.add(0);
//				System.out.println(sourcePoints);
			}
		}
		
	}

	class PaintAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MultiWaveLabel.this.repaint();
		}
		
	}

	class WaveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MultiWaveLabel.this.repaint();
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		currentMousePosition = e.getPoint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		exited = true;
		released = true;
		closeWakeUpTimer();
		closePaintTimer();
		clearAll();
		
		if(e!=null){
			sourcePoint = e.getPoint();
		}
		released = true;
		makeWave();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setWakeUpTimer();
		closePaintTimer();
		setPaintTimer();
		clearAll();
		hasWave = true;
		exited = false;
		exitedAlpha = 150;
		
		released = false;
		this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		exited = true;
		closeWakeUpTimer();
//		closePaintTimer();
//		clearAll();
//		hasWave = false;
		processExit();
		repaint();
		released = false;
		this.repaint();
	}
	
	
}
