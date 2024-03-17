package ca.bcit.comp2522.termproject.javagame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class BlueSlime extends Slime{
    public static final SlimeType SLIME_TYPE = SlimeType.BLUE_SLIME;
    public static final String SLIME_IMAGE = "blueSlime.png";
    public static final int INITIAL_SIZE = 50;

    public BlueSlime(int xPosition, int yPosition){
        super(xPosition, yPosition);
        // 设置黄色粘液的图片
        Image image = new Image(SLIME_IMAGE); // 替换为实际图片路径
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(getRadius() * 2); // 根据Slime大小调整图片大小
        imageView.setFitWidth(getRadius() * 2);

//         确保图片与Slime的中心对齐
        imageView.setX(getCenterX() - getRadius());
        imageView.setY(getCenterY() - getRadius());

        // 将ImageView添加到Slime的父节点中，如果有的话
//        this.getParent().getChildren().add(imageView);
    }
    @Override
    protected SlimeType setConstantSlimeType(SlimeType slimeType) {
        return SLIME_TYPE;
    }

    @Override
    protected String setConstantSlimeImage(String imgName) {
        return SLIME_IMAGE;
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


}