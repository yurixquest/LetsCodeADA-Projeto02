package interfaces;

public interface ImageFileDatabase {

    void saveImageFile(String directory, String content, String nameFile);

    void removeImageFile(String directory, String nameFile);

    void listAllImageFiles(String directory);

}
