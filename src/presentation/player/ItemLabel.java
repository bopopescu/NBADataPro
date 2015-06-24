package presentation.player;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class ItemLabel extends JLabel{
	public static int WORSE = -1;
	public static int UNKNOWN = 0;
	public static int BETTER = 1;
	
	JLabel leftAverageLight;
	JLabel rightAverageLight;
	JLabel leftVarianceLight;
	JLabel rightVarianceLight;
	
	public ItemLabel(String item) {
//		this.setText(item);
//		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(new Font("微软雅黑",1,20));
		this.setForeground(Color.BLACK);
		this.setBackground(Color.LIGHT_GRAY);
		this.setOpaque(true);
		this.setBounds(500,0,200,60);
		this.setLayout(null);
		
		setItemText(item);
		setStable();
	}

	private void setItemText(String text){
		JLabel textLabel = new JLabel();
		textLabel.setFont(new Font("微软雅黑", 1, 20));
		textLabel.setBounds(60, 0, 80, 60);
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setText(text);
		this.add(textLabel);
	}
	
	private void setStable(){
		leftAverageLight = new Light(true);
		leftAverageLight.setLocation(5, 10);
		leftAverageLight.setText("-");
		this.add(leftAverageLight);

		rightAverageLight = new Light(true);
		rightAverageLight.setLocation(170, 10);
		rightAverageLight.setText("-");
		this.add(rightAverageLight);
		

		leftVarianceLight = new Light(false);
		leftVarianceLight.setLocation(30, 10);
		leftVarianceLight.setText("-");
		this.add(leftVarianceLight);

		rightVarianceLight = new Light(false);
		rightVarianceLight.setLocation(145, 10);
		this.add(rightVarianceLight);
		rightVarianceLight.setText("-");

		int avg = (int)(3*Math.random()) - 1;
		int van = (int)(3*Math.random()) - 1;
		changeStable(avg, van);
		
	}
	
	private void changeStable(int average, int variance){
		if(average == -1){
			leftAverageLight.setText("↓");
			rightAverageLight.setText("↑");
		}else if(average == 0){
			leftAverageLight.setText("-");
			rightAverageLight.setText("-");
		}else if(average == 1){
			leftAverageLight.setText("↑");
			rightAverageLight.setText("↓");
		}
		
		if(variance == -1){
			leftVarianceLight.setText("↓");
			rightVarianceLight.setText("↑");
		}else if(variance == 0){
			leftVarianceLight.setText("-");
			rightVarianceLight.setText("-");
		}else if(variance == 1){
			leftVarianceLight.setText("↑");
			rightVarianceLight.setText("↓");
		}
	}
}


class Light extends JLabel{
	public Light(boolean isAverage){
		this.setFont(new Font(Font.DIALOG, Font.BOLD, 35));
		this.setHorizontalAlignment(JLabel.CENTER);
//		this.setForeground(Color.LIGHT_GRAY);
		this.setSize(25, 40);
		
		if(isAverage){
			this.setForeground(Color.RED);
		}else{
			this.setForeground(Color.blue);
		}
	}
}