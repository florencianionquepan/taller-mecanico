package com.besysoft.taller.datos;

import com.besysoft.taller.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DatosDummy {
    public static Cliente getClienteIgor(){
        return new Cliente(null,
                new Persona("Igor","Gatito","462643","Bahia Blanca","8500","calle","300",null,null),
                "462643","igor@yahoo.com",new ArrayList<Vehiculo>());
    }

    public static Cliente getClienteEmma(){
        return new Cliente(null,
                new Persona("Emma","Gatita","462014","Bahia Blanca","8500","calle","300",null,null),
                "462014","emma@yahoo.com",new ArrayList<Vehiculo>());
    }

    public static Vehiculo getVehiculoRenault(){
        return new Vehiculo(null,"AB123CD",2020,"negro",
                "Renault","Twingo",new ArrayList<Cliente>());
    }

    public static Vehiculo getVehiculoFiat(){
        return new Vehiculo(null,"FG789HI",2015,"blanco",
                "Fiat","Panda",new ArrayList<Cliente>());
    }

    public static Administrativo getAdmin(){
        return new Administrativo();
    }

    public static Persona getPersonaUno(){
        return new Persona("Sara","Garcia","+1234",
                "Bahia","8000",null,null,null,null);
    }

    public static Recepcionista getRecep(){
        return new Recepcionista();
    }

    public static Repuesto getRepuestoUno(){
        return new Repuesto(null,"Bosch","Ford Orion",
                "Cilindro principal de freno",new BigDecimal(16532.80),new ArrayList<DetalleOrdenTrabajo>());
    }

    public static Repuesto getRepuestoDos(){
        return new Repuesto(null,"Renault","Megane",
                "Bomba de freno",new BigDecimal(16850.20),new ArrayList<DetalleOrdenTrabajo>());
    }


}
