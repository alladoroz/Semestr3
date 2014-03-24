package Game.WindowObjects;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 07.03.12
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
public class InfoField {
    
    private JLabel player_1;
    private JLabel player_2;
    private JLabel otherInfo;
    private JLabel moveLabel;
    private Box info;
    private Box title;
    private Box value;
    private Box container;
    private JLabel nameTitle_1;
    private JLabel nameTitle_2;
    private final int strut = 6;
    
    public InfoField(String name1, String name2){
        info = Box.createVerticalBox();
        createInfoBoxStrut();
        info.add(new JLabel("Game information:"));
        createInfoBoxStrut();
        info.add(otherInfo = new JLabel(" "));
        createInfoBoxStrut();
        
        container = Box.createHorizontalBox();
        title = Box.createVerticalBox();
        value = Box.createVerticalBox();

        title.add(new JLabel("Move:"));
        value.add(moveLabel = new JLabel());
        createBoxVerticalStrut();
        
        player_1 = new JLabel();
        player_2 = new JLabel();
        nameTitle_1 = new JLabel(name1 + "'s");
        nameTitle_2 = new JLabel(name2 + "'s");
        addPlayerChipsInfo(nameTitle_1, player_1);
        addPlayerChipsInfo(nameTitle_2, player_2);
        
        container.add(title);
        createContainerStrut();
        container.add(value);
        createContainerStrut();

        info.add(container);
        createInfoBoxStrut();
    }

    public void setPlayerName_1(String name){
        nameTitle_1.setText(name + "'s");
    }

    public void setPlayerName_2(String name){
        nameTitle_2.setText(name + "'s");
    }
    
    private void addPlayerChipsInfo(JLabel name, JLabel playerChips){
        title.add(name);
        value.add(playerChips);
        createBoxVerticalStrut();
    }

    private void createInfoBoxStrut(){
        info.add(Box.createVerticalStrut(strut));
    }
    
    private void createContainerStrut(){
        container.add(Box.createVerticalStrut(strut));
    }
    
    private void createBoxVerticalStrut(){
        title.add(Box.createVerticalStrut(strut));
        value.add(Box.createVerticalStrut(strut));
    }
    
    public Box getInfoBox(){
        return info;
    }

    public void setMoveLabel(String info){
        moveLabel.setText(info);
    }

    public void setChipsInfo_1(String info){
        player_1.setText(info);
    }

    public void setChipsInfo_2(String info){
        player_2.setText(info);
    }

    public void setOtherInfo(String info){
        otherInfo.setText(info);
    }
}
