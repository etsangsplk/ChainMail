
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chainmail.Block;
import chainmail.Blockchain;
import chainmail.Client;
import chainmail.Contact;

public class MessageWidget extends JPanel {

	private MainFrame mainFrame;
	private Block block;
	private Blockchain chat;
	
	public MessageWidget(MainFrame mainFrame, Block block, Blockchain chat) {
		
		this.mainFrame = mainFrame;
		this.block = block;
		this.chat = chat;
		this.setBorder(BorderFactory.createEmptyBorder(10, 250, 10, 250));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setMaximumSize(new Dimension(900, 95));
		this.setPreferredSize(new Dimension(900, 95));
		this.setBackground(Color.WHITE);
		
		String message;
		if (this.getChat().length() == 1) {
			message = new String(this.getChat().getHead().getMessage());
		} else {
			byte[] encryptedMessage = this.getChat().getHead().getMessage();
			message = this.getClient().decryptMessage(encryptedMessage);
		}
		JLabel messageLabel = new JLabel(message);
		messageLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(messageLabel);
		
		LocalDateTime timestamp = this.getChat().getHead().getTimestamp();
		if (timestamp != null) {
			JLabel date = new JLabel("Date: " + timestamp.toString());
			date.setAlignmentX(CENTER_ALIGNMENT);
			this.add(date);
		}
	}
	
	public MainFrame getMainFrame() {
		return this.mainFrame;
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	public Client getClient() {
		return this.getMainFrame().getClient();
	}
	
	public Blockchain getChat() {
		return this.chat;
	}

}
