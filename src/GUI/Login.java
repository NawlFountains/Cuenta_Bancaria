package GUI;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import Programa.Cuenta;
import TDACola.ArrayQueue;
import TDACola.Queue;
import TDAPila.LinkedStack;
import TDAPila.Stack;

@SuppressWarnings("serial")
public class Login extends JFrame{
	private JTextField tfApellido;
	private JTextField tfContrasena;
	private JLabel lblResultado;
	
	/*
	 * Metodo para ocultar la interfaz de login
	 * desde el action listener cuando se logea exitosamente
	 */
	private void ocultar() {
		
		this.setVisible(false);
	}
	
	/*
	 * Cheque que la se haya ingresado la contrasena correcta para
	 * la cuenta pasada por parametro
	 * @param Cuenta que se quiere ingresar
	 * @param contrasena de la cuenta
	 * @return True si la contrasena es correcta, false si pasa lo contrario
	 * @throws EmptyStackException si la contrasena esta vacia
	 * @throws EmptyQueueException si la contrasena esta vacia
	 */
	private boolean checkPassword(Cuenta c, String password) throws EmptyStackException, EmptyQueueException {
		//Inicializo las estructuras auxiliares
		Stack<Character> pila1= new LinkedStack<Character>();
		Stack<Character> pila2= new LinkedStack<Character>();
		Queue<Character> cola= new ArrayQueue<Character>();
		int i=0;
		boolean correcto=true;
		//Guardo los caracteres ingresados por el usuario hasta la x
		while (password.charAt(i)!='x') {
			pila1.push(password.charAt(i));
			pila2.push(password.charAt(i));
			cola.enqueue(password.charAt(i));
			i++;
			/*
			 * Si i es el ultimo caracter de la contrasena
			 * y todavia no se leyo x entonces
			 *  la contrasena es incorrecta
			 */
			if (i==password.length()) {
				correcto=false;
				break;
			}
		}
		//Salteo la x
		i++;
		int j=0;
		/*
		 * Chequeo si el apellido es el mismo
		 * que el de la cuenta, si la coal esta vacio entonces
		 * no puede ser correcto porque no ingreso el nombre
		 */
		if (cola.isEmpty())
			correcto=false;
		while (!cola.isEmpty() && correcto) {
			if (cola.dequeue()!=c.getApellido().charAt(j))
				correcto=false;
			j++;
		}
		/*
		 * Voy sacando caracteres de la cola mientras
		 * coincidan con el el apellido inverso,
		 * se realiza dos veces
		 */
		if (password.length()-i<=pila1.size())
			correcto=false;
		while (!pila1.isEmpty() && correcto) {
			if (pila1.pop()!=password.charAt(i))
				correcto=false;
			i++;
		}
		if (password.length()-i<pila2.size())
			correcto=false;
		while (!pila2.isEmpty() && correcto) {
			if (pila2.pop()!=password.charAt(i))
				correcto=false;
			i++;
			
		}
		return correcto;
	}
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150,150,600,200);
		setTitle("Cuenta bancaria");
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblApellido= new JLabel("Ingrese su apellido: ");
		lblApellido.setBounds(10,10,160,15);	//Tamano del label
		lblApellido.setFont(new Font("Arial", Font.PLAIN,12));
		getContentPane().add(lblApellido);
		
		JLabel lblContrasena= new JLabel("Ingrese su contrasena: ");
		lblContrasena.setBounds(10,40,160,15);	//Tamano del label
		lblContrasena.setFont(new Font("Arial", Font.PLAIN,12));
		getContentPane().add(lblContrasena);
		
		tfApellido= new JTextField(); //Creo el text field
		//Configuro la ayuda
		tfApellido.setToolTipText("Aqui va su apellido");
		tfApellido.setBounds(180,7,96,19); //Tamano
		tfApellido.setFont(new Font("Arial", Font.PLAIN,12));
		tfApellido.setColumns(10); //Indicio q el textfield tiene 10 caracteres de ancho
		getContentPane().add(tfApellido);
		
		tfContrasena= new JTextField();
		tfContrasena.setToolTipText("Aqui va su contrasena");
		tfContrasena.setBounds(180,40,96,19); //Tamano
		tfContrasena.setFont(new Font("Arial", Font.PLAIN,12));
		tfContrasena.setColumns(10); //Indicio q el textfield tiene 10 caracteres de ancho
		getContentPane().add(tfContrasena);
		
		lblResultado= new JLabel("");
		lblResultado.setBounds(200,100,250,50);	//Tamano del label
		lblResultado.setFont(new Font("Arial", Font.PLAIN,12));
		getContentPane().add(lblResultado);
		
		//Agrego un boton btnResolver con un oyente
		JButton btnResolver= new JButton("Ingresar");
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sApellido= tfApellido.getText(); //Recupero el numero tiepado en la caja de texto
				String sContrasena= tfContrasena.getText();
				if (sApellido.isEmpty()||sContrasena.isEmpty())
					lblResultado.setText("Lleno todos los campos antes de continuar");
				else {
					Cuenta account= new Cuenta(sApellido);
					boolean correcto=false;
					try {
						correcto=checkPassword(account,sContrasena);
					} catch (EmptyStackException|EmptyQueueException e1) {
						e1.printStackTrace();
					} 
					if (!correcto)
						lblResultado.setText("Contrasena incorrecta,vuelva a intentarlo");
					else {
						lblResultado.setText("Contrasena correcta!");
						App p = new App(account);
						ocultar();
						p.setVisible(true);
						}
				}
				}
		}
		);
		btnResolver.setMnemonic(KeyEvent.VK_R);
		btnResolver.setFont(new Font("Arial",Font.PLAIN,12));
		btnResolver.setBounds(300,7,100,21);
		getContentPane().add(btnResolver);
	}
}
