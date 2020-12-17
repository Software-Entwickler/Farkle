package view;


import controller.FarkleController;
import model.Farkle;

public class MainViewController{

	private  Farkle farkle;
	private FarkleController farkleController;
	private AddPlayersViewController addPlayersViewController;
	
	public MainViewController()
	{
		
		
		
		this.setFarkle(new Farkle());
		this.setFarkleController(new FarkleController());
		this.setAddPlayersViewController(addPlayersViewController);
	}

	public FarkleController getFarkleController() {
		return farkleController;
	}

	public void setFarkleController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}

	public Farkle getFarkle() {
		return farkle;
	}

	public void setFarkle(Farkle farkle) {
		this.farkle = farkle;
	}
	
	public AddPlayersViewController getAddPlayersView() {
		return addPlayersViewController;
	}

	public void setAddPlayersViewController(AddPlayersViewController addPlayersViewController) {
		this.addPlayersViewController = addPlayersViewController;
	}
}
