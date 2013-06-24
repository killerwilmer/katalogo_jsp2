/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scecolombia.utilidades;

import com.scecolombia.logicanegocio.Categoria;
import com.scecolombia.logicanegocio.Imagen;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author cristhianlombana
 */
public class Prueba {

    public Prueba() {
    }

    public static void main(String args[]) throws IOException, SQLException {

        Toolkit.getDefaultToolkit().beep();
        String ruta = "c://temp/pruebacatalogo/";

        File f = new File(ruta);

        String[] ficheros = f.list();

        for (String i : ficheros) {
            File f1 = new File(ruta + "/" + i);


            if (f1.isDirectory()) {
                String nombreCategoria = f1.getName();
                //System.out.println(nombreCategoria);

                Categoria cat = new Categoria();
                cat.setNombre(nombreCategoria);
                cat.setActivo(1);
                cat.setIdFabricante(1);

                Date d = new Date();

                ConexionC con = new ConexionC();
                int fecha = con.ejecutarConsultaUno("SELECT strftime('%s','now','localtime') as tiempo");

                cat.setFechaActualizacion(String.valueOf(fecha));

                int idCat = cat.crear(cat);

                String[] ficheros2 = f1.list();

                for (String j : ficheros2) {
                    File img = new File(ruta + "/" + nombreCategoria + "/" + j);

                    if (img.isFile()) {


                        byte[] arrayByte = new byte[(int) img.length()];
                        try {

                            FileInputStream input = new FileInputStream(img);
                            input.read(arrayByte);

//                            
//                            String dd = new String(arrayByte);
//                            
//                            System.out.print(dd);

                            //FileOutputStream fos = new FileOutputStream(img);
                            //fos.write(arrayByte);

                            ImageIcon imagen = null;
                            Image img2 = null;
                            ImageIcon imagenEsc = null;

                            if (arrayByte != null) {

                                imagen = new ImageIcon(arrayByte);
                                img2 = imagen.getImage();

                                int tam_ideal = 900;
                                int ban = 0;

                                imagenEsc = new ImageIcon(img2.getScaledInstance(img2.getWidth(null), img2.getHeight(null), Image.SCALE_AREA_AVERAGING));
                                
                                while (ban == 0) {
                                    
                                    img2 = imagenEsc.getImage();
                                    
                                    if (img2.getWidth(null) <= tam_ideal && img2.getHeight(null) <= tam_ideal) {
                                        ban = 1;
                                    } else {
                                        
                                        int nancho = img2.getWidth(null) - ((img2.getWidth(null)*10)/100);
                                        int nalto = img2.getHeight(null) - ((img2.getHeight(null)*10)/100);

                                        /*if (img2.getWidth(null) >= img2.getHeight(null)) {
                                            
                                            imagenEsc = new ImageIcon(img2.getScaledInstance(nancho, nalto, Image.SCALE_AREA_AVERAGING));
                                        } else if (img2.getWidth(null) <= img2.getHeight(null)) {
                                            imagenEsc = new ImageIcon(img2.getScaledInstance(nalto, nancho, Image.SCALE_AREA_AVERAGING));
                                        } else if (img2.getWidth(null) == img2.getHeight(null)) {
                                            imagenEsc = new ImageIcon(img2.getScaledInstance(nancho, nancho, Image.SCALE_AREA_AVERAGING));
                                        }*/
                                        
                                        imagenEsc = new ImageIcon(img2.getScaledInstance(nancho, nalto, Image.SCALE_AREA_AVERAGING));
                                        
                                    }
                                }

                                ImageIcon icon = imagenEsc;
                                Image imgParaConvertir = icon.getImage();

                                BufferedImage buf = com.scecolombia.utilidades.BufferImage.getBufferedImage(imgParaConvertir);

                                ByteArrayOutputStream out = new ByteArrayOutputStream();

                                ImageIO.write(buf, "jpg", out);

                                byte[] nuevaimg = out.toByteArray();

                                File fileFinal = new File("c://temp//archivo.jpg");
                                FileOutputStream outFinal = new FileOutputStream(fileFinal);
                                outFinal.write(nuevaimg, 0, nuevaimg.length);

                                //out.write(nuevaimg);
                                outFinal.flush();
                                outFinal.close();


                                Imagen imgParaGuardar = new Imagen();
                                imgParaGuardar.setIdCategoria(idCat);
                                //imgParaGuardar.setImagen(nuevaimg);
                                imgParaGuardar.setImagenArchivo(fileFinal);
                                imgParaGuardar.setImagen(nuevaimg);

                                imgParaGuardar.setFechaActualizacion(String.valueOf(fecha));

                                int id = imgParaGuardar.crear(imgParaGuardar);
                                
                                System.out.println(ruta + "/" + nombreCategoria + "/" + j);
                                System.out.println(id);

                            }

                            
                           Toolkit.getDefaultToolkit().beep();

                            //                        System.out.println(img.getPath());
                            //
                            //                        Imagen imagen = new Imagen();
                            //                        imagen.setIdCategoria(idCat);
                            //                        imagen.crear(imagen);
                            //                        imagen.crear(imagen);
                        } catch (Exception ex) {
                            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println(ex.getMessage());
                        }
                    }


                }

            }

        }

    }
}
