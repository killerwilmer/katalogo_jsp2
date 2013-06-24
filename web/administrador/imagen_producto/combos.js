/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
        <script language="javascript">
        $(document).ready(function(){
                // Parametros para el combo principal
        $("#comboproductos").change(function () {//combo principal donde tenemos los primero datos
                        $("#comboproductos option:selected").each(function () {
                            //alert($(this).val());
                            elegido=$(this).val();//recogemos el valor seleccionado del combo
                            $.post("solo_pro.jsp", { elegido: elegido }, function(data){//pagina de donde traemos la info
                            $("#colpro").html(data);//Este es el div donde se cargara la nueva info
                            });			
                });
        })
        });
        </script>
        
        <script language="javascript">
        $(document).ready(function(){
                // Parametros para el combo principal
        $("#comboimagenes").change(function () {//combo principal donde tenemos los primero datos
                        $("#comboimagenes option:selected").each(function () {
                            //alert($(this).val());
                            elegido=$(this).val();//recogemos el valor seleccionado del combo
                            $.post("solo_img.jsp", { elegido: elegido }, function(data){//pagina de donde traemos la info
                            $("#colimg").html(data);//Este es el div donde se cargara la nueva info
                            });			
                });
        })
        });
        </script>