package ca.bcit.comp2522.termproject.javagame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class YellowSlime extends Slime{
    public static final String YELLOW_SLIME_IMAGE_NAME = "yellowSlime.png";
    public static final SlimeType SLIME_TYPE = SlimeType.YELLOW_SLIME;
    public static final int INITIAL_SIZE = 50;

    public YellowSlime(double xPosition, double yPosition, PetriDish petriDish){
        super(xPosition, yPosition, petriDish);
        //黄色价格上限：10
        this.setPrice(new Random().nextInt(10));
        // 设置黄色粘液的图片


        // 将ImageView添加到Slime的父节点中，如果有的话
//        this.getParent().getChildren().add(imageView);
    }
    @Override
    protected SlimeType setConstantSlimeType(SlimeType slimeType) {
        return SLIME_TYPE;
    }

    @Override
    protected void moveSlime(Slime slime) {

    }

    @Override
    protected ArrayList<Slime> splitSlime(Slime slime) {
        return null;
    }

    @Override
    protected boolean checkIsCollide() {
        return false;
    }

    @Override
    protected Slime slimeMutation() {
        return null;
    }

    @Override
    protected String getConstantSlimeImageName() {
        return YELLOW_SLIME_IMAGE_NAME;
    }


}
