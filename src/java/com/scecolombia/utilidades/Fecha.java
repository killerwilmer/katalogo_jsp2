package com.scecolombia.utilidades;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.util.GregorianCalendar;

public class Fecha
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    private int dia;    
    private int mes;
    private int anio;
    private int semana;
    private int hora;
    private int minuto;
    private int segundo;

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }
    private String diaNombre;
    private String fechaTexto;
    

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    
    
    /**
     * Inicializa una fecha con los par�metros proporcionados <br>
     * <b>post: </b> El objeto fecha tiene sus datos b�sicos asignados con los par�metros proporcionados
     * @param d D�a. d > 0 y d <= 31 y d es un valor v�lido seg�n el mes
     * @param m Mes. m > 0 y m <= 12
     * @param a A�o.
     */
    public void inicializar( int a, int m, int d )
    {
        dia = d;
        mes = m;
        anio = a;
    }

    /**
     * Construye una nueva fecha inicializada en el d�a de hoy. <br>
     * <b>post: </b> El objeto fecha tiene sus datos b�sicos asignados con los datos del d�a de hoy
     */
    public void inicializarHoy( )
    {
        // Usamos un calendario inicializado en el d�a de hoy
        Calendar gc =  Calendar.getInstance();

        // Sacamos los valores de d�a, mes y a�o del calendario
        dia = gc.get( Calendar.DAY_OF_MONTH );
        mes = gc.get( Calendar.MONTH ) + 1;
        anio = gc.get( Calendar.YEAR );
        hora = gc.get(Calendar.HOUR_OF_DAY);
        minuto = gc.get(Calendar.MINUTE);
        segundo = gc.get(Calendar.SECOND);
        
        Dia d  = new Dia();
        diaNombre = d.darDia(String.valueOf(gc.get(Calendar.DAY_OF_WEEK)));

    }

    /**
     * Retorna el d�a de esta fecha
     * @return d�a
     */
    public int darDia( )
    {
        return dia;
    }

    /**
     * Retorna el mes de esta fecha
     * @return mes
     */
    public int darMes( )
    {
        return mes;
    }

    /**
     * Retorna el a�o de esta fecha
     * @return a�o
     */
    public int darAnio( )
    {
        return anio;
    }

    public int darSemana( )
    {
        return semana;
    }


    public String darDiaNombre(){
        return diaNombre;
    }

    /**
     * Este m�todo sirve para dar la diferencia en meses que hay entre dos fechas.
     * @param fecha La fecha contra la que se est� comparando. fecha != null
     * @return Retorna el n�mero de meses
     */
    public int darDiferenciaEnMeses( Fecha fecha )
    {
        int diferencia = 0;

        int otroAnho = fecha.darAnio( );
        int otroMes = fecha.darMes( );
        int otroDia = fecha.darDia( );

        // Calcula la diferencia en meses
        diferencia = 12 * ( otroAnho - anio ) + ( otroMes - mes );

        //Si el d�a no es mayor, resta un mes
        if( otroDia < dia )
            diferencia--;

        return diferencia;
    }

    /**
     * Retorna una cadena que representa la fecha
     * @return La cadena sigue el formato dia-mes-a�o
     */
    public String toStringConHoraExacta( )
    {
        return  "" + anio +  "/" + mes + "/" + dia+" "+minuto+":"+segundo;
    }
    
    public String toStringSinHoraExacta( )
    {
        return  "" + anio +  "/" + mes + "/" + dia;
    }
    
    public void MostrarFecha(String fecha)
    {
        SimpleDateFormat dateformato = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try
        {
            date = dateformato.parse(fecha);
        }
        catch(ParseException e){}

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(date);
        anio = calendario.get(Calendar.YEAR);
        semana = calendario.get(Calendar.WEEK_OF_YEAR);
        System.out.println("Semana: "+calendario.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Año: "+calendario.get(Calendar.YEAR));
        System.out.println("Fecha: "+calendario.getTime());
    }
}