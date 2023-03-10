package models;

import interfaces.FileDatabase;
import interfaces.ImageFileDatabase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

public class FileOrchestrator extends FolderOrchestrator implements ImageFileDatabase, FileDatabase {

    @Override
    public void saveImageFile(String directory, String content, String nameFile) {
        try{
            String dir = "images";
            String diretorioCompleto = directory + "\\" + dir;
            File diretorioFile = new File(diretorioCompleto);
            if(!diretorioFile.exists()){
                System.out.println("O diretório para este tipo de arquivo ainda não existe. Será criado agora");
                diretorioFile.mkdir();
            }
            BufferedImage image = null;
            URL url = new URL(content);
            image = ImageIO.read(url);
            String path = directory +"\\" + dir + "\\" + nameFile + ".jpg";
            ImageIO.write(image, "jpg", new File(path));
            System.out.println("Imagem salva");
        } catch (Exception e){
            System.out.println("Não foi possível salvar a imagem");
        }
    }


    @Override
    public void removeImageFile(String directory, String nameFile) {
        String dir = "image";
        String path = directory +"\\" + dir + "\\" + nameFile + ".jpg";
        File tmp_file = new File(path);
        if(tmp_file.delete()){
            System.out.println("O arquivo " + tmp_file.getName() + " foi deletado");
        } else {
            System.out.println("Não foi possível deletar o arquivo");
        }
    }

    @Override
    public void listAllImageFiles(String directory) {

    }

    public void saveAllListOfFiles(List<MFile> mFileList){

    }

    public void saveAllListOfImageFiles(List<MFile> mImageFilList){}

    @Override
    public void saveFile(String directory, String content, MFileAnnotationTypeEnum typeEnum, String nameFile) {
       try{
            MFileAnnotationTypeEnum dir = typeEnum;
            String diretorioArquivo = dir.toString().toLowerCase();
            String diretorioCompleto = directory + "\\" + diretorioArquivo + "s";
            File diretorioFile = new File(diretorioCompleto);
            if(!diretorioFile.exists()){
                System.out.println("O diretório para este tipo de arquivo ainda não existe. Será criado agora");
                diretorioFile.mkdir();
            }
            if(diretorioArquivo.equals("simple")){
                FileWriter texto = new FileWriter(directory + "\\" + nameFile + ".txt");
                PrintWriter gravarTexto= new PrintWriter(texto);
                gravarTexto.printf(content);
                texto.close();
                System.out.println("Arquivo do tipo: " + typeEnum + "  criado");
            } else {
                FileWriter texto = new FileWriter(directory + "\\" + dir + "s" + "\\" + nameFile + ".txt");
                PrintWriter gravarTexto= new PrintWriter(texto);
                gravarTexto.printf(content);
                texto.close();
                System.out.println("Arquivo do tipo: " + typeEnum + "  criado");
            }
        } catch (Exception e){
            System.out.println("Não foi possível criar este arquivo");
        }
    }

    @Override
    public void recoveryFile(String directory) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(directory));
        String linha = "";
        while (true) {
            if (linha != null) {
                System.out.println(linha);

            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();{    
    }
    
    @Override
    public void removeFile(String directory, String nameFile, MFileAnnotationTypeEnum typeEnum) {
        MFileAnnotationTypeEnum dir = typeEnum;
        String diretorioEnum = dir.toString().toLowerCase();
        String path = directory +"\\" + diretorioEnum + "s" + "\\" + nameFile + ".txt";
        File tmp_file = new File(path);
        if(tmp_file.delete()){
            System.out.println("O arquivo " + tmp_file.getName() + " foi deletado");
        } else {
            System.out.println("Não foi possível deletar o arquivo");
        }
    }
    @Override
    public void listAllFiles(String directory) {
        File file = new File(directory);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            System.out.println(arquivos.getName());
        }
    }
}
