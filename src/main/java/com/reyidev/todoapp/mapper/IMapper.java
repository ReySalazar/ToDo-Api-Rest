package com.reyidev.todoapp.mapper;

public interface IMapper <I, O>{
    public O map(I in);  // Permite mapear todo lo que entra por I-input a O-output
}
