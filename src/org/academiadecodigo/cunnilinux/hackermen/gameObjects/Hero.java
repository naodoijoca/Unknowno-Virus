package org.academiadecodigo.cunnilinux.hackermen.gameObjects;

import org.academiadecodigo.cunnilinux.hackermen.AssetPaths;
import org.academiadecodigo.cunnilinux.hackermen.map.Canvas;
import org.academiadecodigo.cunnilinux.hackermen.map.Direction;
import org.academiadecodigo.cunnilinux.hackermen.map.Position;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Hero {

    private int xPosition;
    private Picture heroRight;
    private Picture heroLeft;
    private HeroInputs heroInputs;
    private Direction direction;
    private boolean dead;

    public Hero(int xPosition) {
        this.xPosition = xPosition;
        heroLeft = new Picture(xPosition, Canvas.FLOOR_LEVEL, AssetPaths.HERO_LEFT);
        heroRight = new Picture(xPosition, Canvas.FLOOR_LEVEL, AssetPaths.HERO_RIGHT);
        heroInputs = new HeroInputs(new HeroMovement(this));
        direction = Direction.RIGHT;
        dead = false;

    }

    public int getX() {
        return xPosition;
    }

    public Picture getHero() {

        if (direction == Direction.RIGHT) {
            return heroRight;
        } else {
            return heroLeft;
        }

    }

    public void draw() {

        if (direction == Direction.RIGHT) {
            heroLeft.delete();
            heroRight.draw();
        } else {
            heroRight.delete();
            heroLeft.draw();
        }

    }

    private void shootProjectiles() {
        //Projectile projectile = new Projectile(pos.setCol(3),pos.setRow(3));
        //projectile.draw();
    }

    private void moveLeft() {

        setDirection(Direction.LEFT);
        draw();
        heroLeft.translate(-Canvas.CELL_SIZE, 0);
        heroRight.translate(-Canvas.CELL_SIZE, 0);

    }

    private void moveRight() {

        setDirection(Direction.RIGHT);
        draw();
        heroLeft.translate(Canvas.CELL_SIZE, 0);
        heroRight.translate(Canvas.CELL_SIZE, 0);

    }

    private class HeroInputs {
        public HeroInputs(KeyboardHandler heroMovement) {

            Keyboard keyboard = new Keyboard(heroMovement);

            /*
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
            */

            int[] keysArray = {KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_SPACE};
            addEventListener(keyboard, keysArray);

        }

        public void addEventListener(Keyboard keyboard, int[] keysArray) {
            for (int key : keysArray) {
                keyboard.addEventListener(createKeyboardEvent(key, KeyboardEventType.KEY_PRESSED));
            }
        }

        public KeyboardEvent createKeyboardEvent(int keyboardEventKey, KeyboardEventType keyboardEventType) {
            KeyboardEvent keyboardEvent = new KeyboardEvent();
            keyboardEvent.setKey(keyboardEventKey);
            keyboardEvent.setKeyboardEventType(keyboardEventType);
            return keyboardEvent;
        }

    }

    public class HeroMovement implements KeyboardHandler {

        private Hero hero;

        private HeroMovement(Hero hero) {
            this.hero = hero;
        }

        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {

            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_LEFT:
                    hero.moveLeft();
                    break;
                case KeyboardEvent.KEY_RIGHT:
                    hero.moveRight();
                    break;
                case KeyboardEvent.KEY_SPACE:
                default:
                    shootProjectiles();
                    break;

            }

        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {
        }

    }

    public Rectangle bounds() {
        return new Rectangle(heroRight.getX(), heroRight.getY(),
                heroRight.getWidth(), heroRight.getHeight());
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

