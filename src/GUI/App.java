package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Programa.Cuenta;

@SuppressWarnings("serial")
public class App extends JFrame {
	private JTextField tfTransacciones;
	private JTextField tfConsulta;
	public App(Cuenta acc) {
		Cuenta account=acc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,200,1280,720);
		setTitle("Cuenta bancaria");
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblBienvenido= new JLabel("Bienvenido "+account.getApellido());
		lblBienvenido.setBounds(100,300,370,100);
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN,18));
		getContentPane().add(lblBienvenido);
		
		JLabel lblSaldo= new JLabel("Saldo : 0");
		lblSaldo.setFont(new Font("Tahoma", Font.PLAIN,18));
		lblSaldo.setBounds(800,300,250,100);
		getContentPane().add(lblSaldo);
		
		JLabel lblOperacionReciente= new JLabel("Operacion mas reciente: ");
		lblOperacionReciente.setFont(new Font("Tahoma", Font.PLAIN,18));
		lblOperacionReciente.setBounds(730,70,290,100);
		getContentPane().add(lblOperacionReciente);
		
		JLabel lblOperacionAntigua= new JLabel("Operacion mas antigua: ");
		lblOperacionAntigua.setFont(new Font("Tahoma", Font.PLAIN,18));
		lblOperacionAntigua.setBounds(730,130,250,100);
		getContentPane().add(lblOperacionAntigua);
		
		JTextArea taHistorial= new JTextArea();
		taHistorial.setFont(new Font("Tahoma",Font.PLAIN,16));
		JScrollPane scrollPane= new JScrollPane();
		scrollPane.setBounds(1050,30,200,600);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(taHistorial);
		taHistorial.setToolTipText("Historial de transacciones siendo arriba del todo la mas reciente");
		
		tfTransacciones= new JTextField();
		tfTransacciones.setToolTipText("Ingrese lo que quiere depositar o retirar");
		tfTransacciones.setBounds(100,500,150,19); //Tamano
		tfTransacciones.setFont(new Font("Arial", Font.PLAIN,18));
		tfTransacciones.setColumns(15); //Indicio q el textfield tiene 10 caracteres de ancho
		getContentPane().add(tfTransacciones);
		
		JButton btDepositar= new JButton("Depositar / Retirar ");
		btDepositar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String sMonto= tfTransacciones.getText(); //Recupero el numero tiepado en la caja de texto
				float dMonto= Float.parseFloat(sMonto);
				if (dMonto>0) {
					account.deposito(dMonto);
					lblSaldo.setText("Saldo : "+account.getSaldo());
					taHistorial.append("Deposito : "+dMonto+"\n");
					lblOperacionReciente.setText("Operacion mas reciente: "+account.operacionReciente());
					if (!account.getHistorial().isEmpty()) {	//Si ya se hicieron transacciones
						lblOperacionAntigua.setText("Operacion mas antigua: "+account.operacionAntigua());
					}
					}
				else {
					account.extraccion(dMonto);
					lblSaldo.setText("Saldo : "+account.getSaldo());
					taHistorial.append("Extraccion : "+Math.abs(dMonto)+"\n");
					lblOperacionReciente.setText("Operacion mas reciente: "+account.operacionReciente());
					if (!account.getHistorial().isEmpty()) {		//Si ya se hicieron transacciones
						lblOperacionAntigua.setText("Operacion mas antigua: "+account.operacionAntigua());
					}
				}
					
			}
			
		});
		btDepositar.setMnemonic(KeyEvent.VK_R);
		btDepositar.setFont(new Font("Arial",Font.PLAIN,18));
		btDepositar.setBounds(320,500,200,19);
		getContentPane().add(btDepositar);
		
		tfConsulta= new JTextField();
		tfConsulta.setToolTipText("Ingrese su consulta");
		tfConsulta.setBounds(20,150,150,19); //Tamano
		tfConsulta.setFont(new Font("Arial", Font.PLAIN,18));
		tfConsulta.setColumns(18); //Indicio q el textfield tiene 10 caracteres de ancho
		getContentPane().add(tfConsulta);
		
		JLabel lblConsulta= new JLabel("Cantidad de transacciones con el monto ingresado : ");
		lblConsulta.setFont(new Font("Tahoma", Font.PLAIN,18));
		lblConsulta.setBounds(20,30,500,100);
		getContentPane().add(lblConsulta);
		
		JButton btConsulta= new JButton("Consultar");
		btConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sConsulta= tfConsulta.getText();
				Float fConsulta= Float.parseFloat(sConsulta);
				lblConsulta.setText("Cantidad de transacciones con el monto ingresado : "+account.buscarTransacciones(fConsulta));
			}
			
		});
		btConsulta.setMnemonic(KeyEvent.VK_R);
		btConsulta.setFont(new Font("Arial",Font.PLAIN,18));
		btConsulta.setBounds(190,150,120,19);
		getContentPane().add(btConsulta);
		
		
	}
}
