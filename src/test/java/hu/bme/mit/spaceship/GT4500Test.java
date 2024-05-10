package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockp;
  private TorpedoStore mocks;


  @BeforeEach
  public void init(){
    
    mockp=mock(TorpedoStore.class);
    mocks=mock(TorpedoStore.class);


    this.ship = new GT4500(mockp, mocks);
    
    
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    //mockp.fire(1);
    verify(mockp,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockp.fire(1)).thenReturn(true);
    when(mocks.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);


    // Assert
    verify(mockp,times(1)).fire(1);
    verify(mocks,times(1)).fire(1);


    assertEquals(true, result);
  }

  @Test
  public void single_fire(){
    // Arrange
    when(mockp.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);


    // Assert
    verify(mockp,times(1)).fire(1);


    assertEquals(true, result);
  }

  @Test
  public void alternating(){
    // Arrange
    when(mockp.fire(1)).thenReturn(true);
    when(mocks.fire(1)).thenReturn(true);
    



    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);




    // Assert
    verify(mockp,times(1)).fire(1);
    verify(mocks,times(1)).fire(1);



    assertEquals(true, result);
  }

  @Test
  public void first_empty(){
    // Arrange
    when(mockp.isEmpty()).thenReturn(true);
    when(mocks.isEmpty()).thenReturn(false);
    when(mocks.fire(1)).thenReturn(true);
    



    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);




    // Assert
    verify(mockp,times(0)).fire(1);
    verify(mocks,times(1)).fire(1);



    assertEquals(true, result);
  }

  @Test
  public void second_empty(){
    // Arrange
    when(mockp.isEmpty()).thenReturn(false);
    when(mocks.isEmpty()).thenReturn(true);
    when(mockp.fire(1)).thenReturn(true);
    



    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);




    // Assert
    verify(mockp,times(3)).fire(1);
    verify(mocks,times(0)).fire(1);



    assertEquals(true, result);
  }

  @Test
  public void all_empty(){
    // Arrange
    when(mockp.isEmpty()).thenReturn(true);
    when(mocks.isEmpty()).thenReturn(true);
    when(mockp.fire(1)).thenReturn(false);
    when(mockp.fire(1)).thenReturn(false);

    



    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);




    // Assert
    verify(mockp,times(0)).fire(1);
    verify(mocks,times(0)).fire(1);



    assertEquals(false, result);
  }

  @Test
  public void primary_again_but_empty(){
    when(mockp.isEmpty()).thenReturn(false);
    when(mocks.isEmpty()).thenReturn(true);
    when(mockp.fire(1)).thenReturn(true);
    



    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    when(mockp.isEmpty()).thenReturn(true);
    result = ship.fireTorpedo(FiringMode.SINGLE);




    // Assert
    verify(mockp,times(1)).fire(1);
    verify(mocks,times(0)).fire(1);



    assertEquals(false, result);
  }


}
