package br.com.ifood.fotoproduto.infra;

import br.com.ifood.core.storage.StorageProperties;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.net.URL;

@Repository
@RequiredArgsConstructor
@Log4j2
public class S3FotoStorage implements FotoStorage {

        private final AmazonS3 amazonS3;
        private final StorageProperties storageProperties;

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        log.info("[inicia] S3FotoStorage - recuperar");
        String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);
        log.info("[finaliza] S3FotoStorage - recuperar");
        return FotoRecuperada.builder()
                .url(url.toString())
                .build();
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        log.info("[inicia] S3FotoStorage - armazenar");
        try {
         String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());

         var objectMetaData = new ObjectMetadata();
         objectMetaData.setContentType(novaFoto.getContentType());

         var putObjectRequest = new PutObjectRequest(
                 storageProperties.getS3().getBucket(),
                 caminhoArquivo,
                 novaFoto.getInputStream(),
                 objectMetaData)
                 .withCannedAcl(CannedAccessControlList.PublicRead);

         amazonS3.putObject(putObjectRequest);
         log.info("[finaliza] S3FotoStorage - armazenar");
     } catch (Exception e) {
         throw new StorageException("Não foi possivel enviar arquivo para Amazon S3.", e);
     }

    }

    @Override
    public void remover(String nomeArquivo) {
        log.info("[inicia] S3FotoStorage - remover");
        try {
            String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

            var deleteObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getBucket(), caminhoArquivo);

            amazonS3.deleteObject(deleteObjectRequest);
            log.info("[finaliza] S3FotoStorage - remover");
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
        }
    }

    private String getCaminhoArquivo(String nomeArquivo) {
        return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(),nomeArquivo);
    }
}
