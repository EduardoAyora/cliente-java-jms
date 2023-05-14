package ec.edu.ups.appdis.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jboss.tools.examples.service.MessageServerRemote;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtEmail;
	private JTextField txtMessage;
	
	private MessageServerRemote messageServerRemote;
	DefaultListModel<String> model = new DefaultListModel();
	private String user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.instanciar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void instanciar() throws Exception {
		try {  
          final Hashtable<String, Comparable> jndiProperties =  
                  new Hashtable<String, Comparable>();  
          jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                  "org.wildfly.naming.client.WildFlyInitialContextFactory");  
          jndiProperties.put("jboss.naming.client.ejb.context", true);  
            
          jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
          jndiProperties.put(Context.SECURITY_PRINCIPAL, "eduardo");  
          jndiProperties.put(Context.SECURITY_CREDENTIALS, "eduardo");  
            
          final Context context = new InitialContext(jndiProperties);  
            
          final String lookupName = "ejb:/jboss-javaee-webapp/MessageServer!org.jboss.tools.examples.service.MessageServerRemote";  
            
          this.messageServerRemote = (MessageServerRemote) context.lookup(lookupName);
          
      } catch (Exception ex) {
          ex.printStackTrace();
          throw ex;
      }
	}
	
	public void message() throws Exception {
		if (user != null) {
			this.messageServerRemote.sendMessage(txtMessage.getText(), txtEmail.getText());			
		}
	}
	
	public void receive() throws Exception {
		if (user != null) {
			String message = this.messageServerRemote.listMessages(user);	
			model.addElement(message);
		}
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel_4.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		
		JLabel lblNewLabel = new JLabel("Ingrese su usuario:");
		panel_9.add(lblNewLabel);
		
		final JLabel lblUser = new JLabel("");
		panel_9.add(lblUser);
		
		txtUser = new JTextField();
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user = txtUser.getText();
				try {
					receive();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblUser.setText(user);
			}
		});
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_4.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Envíe un correo:");
		panel_2.add(lblNewLabel_1);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_7.add(panel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		panel_5.add(lblNewLabel_2);
		
		txtEmail = new JTextField();
		panel_5.add(txtEmail);
		txtEmail.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_7.add(panel_6);
		
		JLabel lblNewLabel_3 = new JLabel("Mensaje:");
		panel_6.add(lblNewLabel_3);
		
		txtMessage = new JTextField();
		panel_6.add(txtMessage);
		txtMessage.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
		
		JButton btnNewButton_1 = new JButton("Enviar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					message();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_8.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		JList list = new JList(model);
		panel_3.add(list);
		
		
	}
}