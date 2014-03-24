package Game.WindowObjects;

import javax.swing.*;

/**
 * Info field
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

    /**
     * Constructor
     * @param name1 player1 name
     * @param name2 player2 name
     */
    public InfoField(String name1, String name2) {
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

    /**
     * Player chips info
     * @param name Player name
     * @param playerChips Player chips
     */
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
    
    private void createBoxVerticalStrut() {
        title.add(Box.createVerticalStrut(strut));
        value.add(Box.createVerticalStrut(strut));
    }

    //Methods to set info

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
