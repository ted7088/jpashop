package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Album extends Item {
    private String artist;
    private String etc;
    
    public Album() {
        this.setDtype("Album");
    }
}
