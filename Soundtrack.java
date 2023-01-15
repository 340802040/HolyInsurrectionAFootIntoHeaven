import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Soundtrack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Soundtrack extends Actor
{
    public static GreenfootSound aSweepingFog = new GreenfootSound("sounds/A_Sweeping_Fog.mp3");
    public static GreenfootSound anInsurmountableHindrance = new GreenfootSound("An_Insurmountable_Hindrance.mp3");
    public static GreenfootSound farInForeignLands = new GreenfootSound("Far_In_Foreign_Lands.mp3");
    public static GreenfootSound inAnUnfalteringField = new GreenfootSound("In_An_Unfaltering_Field.mp3");
    public static GreenfootSound intrusiveRevolutionary = new GreenfootSound("Intrusive_Revolutionary.mp3");
    public static GreenfootSound lullabyOfFairies = new GreenfootSound("Lullaby_of_Fairies.mp3");
    public static GreenfootSound meadowOfDahlias = new GreenfootSound("Meadow_of_Dahlias.mp3");
    public static GreenfootSound theStrolling = new GreenfootSound("The_Strolling.mp3");
    
    public static void setVolume() {
        aSweepingFog.setVolume(80);
    }
    
    /**
     * Stops music
     */
    public static void stopAll() {
        aSweepingFog.stop();
        anInsurmountableHindrance.stop();
        farInForeignLands.stop();
        inAnUnfalteringField.stop();
        intrusiveRevolutionary.stop();
        lullabyOfFairies.stop();
        meadowOfDahlias.stop();
        theStrolling.stop();
    }
    
    /**
     * Stops music except A Sweeping Fog
     */
    public static void stopAllExceptASweepingFog() {
        anInsurmountableHindrance.stop();
        farInForeignLands.stop();
        inAnUnfalteringField.stop();
        intrusiveRevolutionary.stop();
        lullabyOfFairies.stop();
        meadowOfDahlias.stop();
        theStrolling.stop();
    }
    
    /**
     * Stops music except An Insurmountable Hindrance
     */
    public static void stopAllExceptAnInsurmountableHindrance() {
        aSweepingFog.stop();
        farInForeignLands.stop();
        inAnUnfalteringField.stop();
        intrusiveRevolutionary.stop();
        lullabyOfFairies.stop();
        meadowOfDahlias.stop();
        theStrolling.stop();
    }
    
    /**
     * Stops music except Far In Foreign Lands
     */
    public static void stopAllExceptFarInForeignLands() {
        aSweepingFog.stop();
        anInsurmountableHindrance.stop();
        inAnUnfalteringField.stop();
        intrusiveRevolutionary.stop();
        lullabyOfFairies.stop();
        meadowOfDahlias.stop();
        theStrolling.stop();
    }
    
    /**
     * Stops music except In An Unfaltering Field
     */
    public static void stopAllExceptInAnUnfalteringField() {
        aSweepingFog.stop();
        anInsurmountableHindrance.stop();
        farInForeignLands.stop();
        intrusiveRevolutionary.stop();
        lullabyOfFairies.stop();
        meadowOfDahlias.stop();
        theStrolling.stop();
    }
    
    /**
     * Stops music except Intrusive Revolutionary
     */
    public static void stopAllExceptIntrusiveRevolutionary() {
        aSweepingFog.stop();
        anInsurmountableHindrance.stop();
        farInForeignLands.stop();
        inAnUnfalteringField.stop();
        lullabyOfFairies.stop();
        meadowOfDahlias.stop();
        theStrolling.stop();
    }
    
    /**
     * Stops music except Lullaby of Fairies
     */
    public static void stopAllExceptLullabyOfFairies() {
        aSweepingFog.stop();
        anInsurmountableHindrance.stop();
        farInForeignLands.stop();
        inAnUnfalteringField.stop();
        intrusiveRevolutionary.stop();
        meadowOfDahlias.stop();
        theStrolling.stop();
    }
    
    /**
     * Stops music except Meadow of Dahlias
     */
    public static void stopAllExceptMeadowOfDahlias() {
        aSweepingFog.stop();
        anInsurmountableHindrance.stop();
        farInForeignLands.stop();
        inAnUnfalteringField.stop();
        intrusiveRevolutionary.stop();
        lullabyOfFairies.stop();
        theStrolling.stop();
    }
    
    /*
     * Stops music except The Strolling
     */
    public static void stopAllExceptTheStrolling() {
        aSweepingFog.stop();
        anInsurmountableHindrance.stop();
        farInForeignLands.stop();
        inAnUnfalteringField.stop();
        intrusiveRevolutionary.stop();
        lullabyOfFairies.stop();
        meadowOfDahlias.stop();
    }
}
