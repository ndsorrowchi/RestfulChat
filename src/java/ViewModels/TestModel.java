/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author chiming
 */

public class TestModel {
    private String id;
    
    public TestModel(){}
    public TestModel(String id){this.id=id;}
    
    public String getId(){return id;}
    public void setId(String id){this.id=id;}
}
