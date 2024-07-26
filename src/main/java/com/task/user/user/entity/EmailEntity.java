package com.task.user.user.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
// Annotations
@Data
@NoArgsConstructor
@Getter
@Setter
 
// Class
public class EmailEntity{
 
    // Class data members
    private String recipient;
    private String msgBody;
    private String attachment;
}