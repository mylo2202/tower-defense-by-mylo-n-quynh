package game;

import game.characters.*;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//should this class be abstract?

public  class GameEntity
{
    //methods for getting coordinates and sizes go here
    private Point2D coordinate;
  /*  private int posX;
    private int posY;*/
    private ImageView view = new ImageView();
    private boolean alive =true;
    private Point2D velocity= new Point2D(0,0);
    public GameEntity() {

    }

    GameEntity(double posX, double posY)
    {
        coordinate= new Point2D(posX,posY);
    }
    GameEntity(ImageView View){
        this.view=View;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public  boolean isDead(){
        return !alive;
    }
   /* public final void setPosX(int posX)
    {
        this.posX = posX;
    }
*/
    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    /*public final void setPosY(int posY)
    {
        this.posY = posY;
    }*/
    public double getRotate(){
        return view.getRotate();
    }


    public void update(){
        view.setTranslateX(view.getTranslateX()+velocity.getX());
        view.setTranslateY(view.getTranslateY()+velocity.getY());
        System.out.println(velocity.getX()+"      "+velocity.getY());
    }
    public ImageView getView(){
        return view;
    }
    public void setView(ImageView imageView){
        view =imageView;
    }
    public  boolean isColliding(GameEntity other){
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }

    //movement methods maybe go here
}