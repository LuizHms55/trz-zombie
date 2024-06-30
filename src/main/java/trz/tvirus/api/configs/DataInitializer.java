//package trz.tvirus.api.configs;
//
//import lombok.AllArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import trz.tvirus.domain.model.Localizacao;
//import trz.tvirus.domain.model.Sobrevivente;
//import trz.tvirus.domain.repository.SobreviventeRepository;
//import trz.tvirus.domain.utils.enums.Genero;
//
//
//@Component
//@AllArgsConstructor
//public class DataInitializer implements CommandLineRunner {
//
//    private final SobreviventeRepository sobreviventeRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Sobrevivente sobrevivente = new Sobrevivente();
//        sobrevivente.setNome("Vitor");
//        sobrevivente.setGenero(Genero.MASCULINO);
//        Localizacao localizacao = new Localizacao();
//        sobrevivente.setLocalizacao(Genero.MASCULINO);
//    }
//}
