package org.academiadecodigo.cunnilinux.hackermen.gameObjects;

import org.academiadecodigo.cunnilinux.hackermen.AssetPaths;
import org.academiadecodigo.cunnilinux.hackermen.Canvas;
import org.academiadecodigo.cunnilinux.hackermen.Position;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Hero {

    private Position pos;
    private Picture heroRight;
    private Picture heroLeft;

    private HeroInputs heroInputs;
    private Direction direction;
    private boolean dead;

    public Hero(int col, int row) {
        pos = new Position(col, row);
        heroLeft = new Picture(pos.colToX(), pos.rowToY(), AssetPaths.HERO_LEFT);
        heroRight = new Picture(pos.colToX(), pos.rowToY(), AssetPaths.HERO_RIGHT);
        heroInputs = new HeroInputs(new HeroMovement(this));
        dead = false;
    }

    public Position getPos() {
        return pos;
    }

    public Picture getHero() {
        return hero;
    }

    public void draw() {
        hero.draw();
    }
    private void shootProjectiles(){
        //Projectile projectile = new Projectile(pos.setCol(3),pos.setRow(3));
        //projectile.draw();
    }
    private void moveLeft() {
        //pos.setCol(pos.getCol() - 1);
        hero.load("resources/claudioTiroEsquerda.png");
        hero.translate(-Canvas.CELL_SIZE, 0);
        // if (this.getPos().getCol() == 0) {
        //   return;
        //}

    }

    private void moveRight() {

        //pos.setCol(pos.getCol() + 1);
        hero.load("resources/claudioTiroDireita.png");
        hero.translate(Canvas.CELL_SIZE, 0);
        // if (this.getPos().getCol() == 10) {
        // return;
        //}

    }

    private class HeroInputs {
        private HeroInputs(KeyboardHandler heroMovement) {

            Keyboard keyboard = new Keyboard(heroMovement);

            KeyboardEvent left = new KeyboardEvent();
            KeyboardEvent right = new KeyboardEvent();
            KeyboardEvent space = new KeyboardEvent();

            right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            right.setKey(KeyboardEvent.KEY_RIGHT);
            left.setKey(KeyboardEvent.KEY_LEFT);
            space.setKey(KeyboardEvent.KEY_SPACE);

            keyboard.addEventListener(left);
            keyboard.addEventListener(right);
            keyboard.addEventListener(space);
        }

    }

    public class HeroMovement implements KeyboardHandler {

        private Hero hero;

        private HeroMovement(Hero hero) {
            this.hero = hero;
        }

        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
                hero.moveLeft();
            }

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
                hero.moveRight();
            }
            if(keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE){
                shootProjectiles();
            }
        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {
        }

    }

    public boolean bounds() {
        //return new Rectangle();
        return true;
    }

}
