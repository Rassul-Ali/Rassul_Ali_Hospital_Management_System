package com.javafx.hospital;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AdminController implements Initializable {

    // Constants
    private static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
    private static final int TIME_UPDATE_DELAY = 1; // seconds
    private AlertMessage alertMessage;
    private Button selectedButton;

    // Thread management
    private ScheduledExecutorService scheduler;

    // FXML Components
    @FXML
    private TableView<?> admin_alta_semanal;

    @FXML
    private TableColumn<?, ?> admin_alta_semanal_doenca;

    @FXML
    private TableColumn<?, ?> admin_alta_semanal_medico;

    @FXML
    private TableColumn<?, ?> admin_alta_semanal_nomePa;

    @FXML
    private TableColumn<?, ?> admin_alta_semanal_quarto;

    @FXML
    private TableColumn<?, ?> admin_alta_semanal_sexo;

    @FXML
    private BarChart<?, ?> admin_bar_paci_internados;

    @FXML
    private Button admin_btn_estagiario;

    @FXML
    private Button admin_btn_pacientes;

    @FXML
    private Button admin_btn_tecnicos;

    @FXML
    private PieChart admin_chart_doencas;

    @FXML
    private Button admin_conta;

    @FXML
    private AnchorPane admin_conta_form;

    @FXML
    private Button admin_dashboard;

    @FXML
    private AnchorPane admin_dashboard_form;

    @FXML
    private Label admin_date;

    @FXML
    private TableView<?> admin_esta_table;

    @FXML
    private TableColumn<?, ?> admin_esta_table_apelido;

    @FXML
    private TableColumn<?, ?> admin_esta_table_celular;

    @FXML
    private TableColumn<?, ?> admin_esta_table_curso;

    @FXML
    private TableColumn<?, ?> admin_esta_table_endero;

    @FXML
    private TableColumn<?, ?> admin_esta_table_idade;

    @FXML
    private TableColumn<?, ?> admin_esta_table_idade1;

    @FXML
    private TableColumn<?, ?> admin_esta_table_intituto;

    @FXML
    private TableColumn<?, ?> admin_esta_table_pNome;

    @FXML
    private TableColumn<?, ?> admin_esta_table_sexo;

    @FXML
    private Button admin_estagiario;

    @FXML
    private AnchorPane admin_estagiario_form;

    @FXML
    private ComboBox<?> admin_filtar_estagiario;

    @FXML
    private ComboBox<?> admin_filtro_pacientes;

    @FXML
    private Label admin_form_text;

    @FXML
    private TableView<?> admin_interna_semanal;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_Medico;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_doenca;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_idade;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_nomePa;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_quarto;

    @FXML
    private TableColumn<?, ?> admin_interna_semanal_sexo;

    @FXML
    private TextField admin_label_estagiario;

    @FXML
    private TextField admin_label_pacientes;

    @FXML
    private TextField admin_label_tecnicos;

    @FXML
    private StackPane admin_main;

    @FXML
    private AnchorPane admin_main_form;

    @FXML
    private TableView<?> admin_med_table;

    @FXML
    private TableColumn<?, ?> admin_med_table_Celular;

    @FXML
    private TableColumn<?, ?> admin_med_table_End;

    @FXML
    private TableColumn<?, ?> admin_med_table_Espc;

    @FXML
    private TableColumn<?, ?> admin_med_table_Idade;

    @FXML
    private TableColumn<?, ?> admin_med_table_Sexo;

    @FXML
    private TableColumn<?, ?> admin_med_table_apelido;

    @FXML
    private TableColumn<?, ?> admin_med_table_emal;

    @FXML
    private TableColumn<?, ?> admin_med_table_pNome;

    @FXML
    private Button admin_medicos;

    @FXML
    private AnchorPane admin_medicos_form;

    @FXML
    private TableView<?> admin_pacie_table;

    @FXML
    private TableColumn<?, ?> admin_pacie_table_Idade;

    @FXML
    private TableColumn<?, ?> admin_pacie_table_Sexo;

    @FXML
    private TableColumn<?, ?> admin_pacie_table_celular;

    @FXML
    private TableColumn<?, ?> admin_pacie_table_endereco;

    @FXML
    private TableColumn<?, ?> admin_pacie_table_nome;

    @FXML
    private TableColumn<?, ?> admin_pacie_table_status;

    @FXML
    private TableColumn<?, ?> admin_pacie_table_trabalho;

    @FXML
    private Button admin_pacientes;

    @FXML
    private AnchorPane admin_pacientes_form;

    @FXML
    private Button admin_registar;

    @FXML
    private AnchorPane admin_registar_form;

    @FXML
    private Button admin_relatorio;

    @FXML
    private AnchorPane admin_relatorios_form;

    @FXML
    private Button admin_sair;

    @FXML
    private Button admin_tecnicos;

    @FXML
    private AnchorPane admin_tecnicos_form;

    @FXML
    private TableView<?> admin_tecnicos_table;

    @FXML
    private TableColumn<?, ?> admin_tecnicos_table_apelido;

    @FXML
    private TableColumn<?, ?> admin_tecnicos_table_celular;

    @FXML
    private TableColumn<?, ?> admin_tecnicos_table_email;

    @FXML
    private TableColumn<?, ?> admin_tecnicos_table_endereco;

    @FXML
    private TableColumn<?, ?> admin_tecnicos_table_especializacao;

    @FXML
    private TableColumn<?, ?> admin_tecnicos_table_idade;

    @FXML
    private TableColumn<?, ?> admin_tecnicos_table_pNome;

    @FXML
    private TableColumn<?, ?> admin_tecnicos_table_sexo;

    @FXML
    private Label admin_total_medicos;

    @FXML
    private Label admin_total_paci_falecidos;

    @FXML
    private Label admin_total_paci_internado;

    @FXML
    private Label admin_total_pacientes;

    @FXML
    private Label admin_user;

    @FXML
    private Label conta_data_criacao;

    @FXML
    private Label conta_data_nascimento;

    @FXML
    private Label conta_email;

    @FXML
    private ImageView conta_image;

    @FXML
    private Label conta_nome;

    @FXML
    private Label conta_nome_usuario;

    @FXML
    private Label conta_palavra_passe;

    @FXML
    private TextField editar_apelido;

    @FXML
    private PasswordField editar_confirmar_palavra;

    @FXML
    private TextField editar_email;

    @FXML
    private ImageView editar_image;

    @FXML
    private Button editar_importar_image;

    @FXML
    private TextField editar_pNome;

    @FXML
    private PasswordField editar_palavra_passe;

    @FXML
    private Button editar_salvar;

    @FXML
    private Button pesquisa_btn_medico;

    @FXML
    private ComboBox<?> pesquisa_filtrar_medico;

    @FXML
    private TextField pesquisa_label_medico;

    @FXML
    private TextField reg_estag_BI;

    @FXML
    private TextField reg_estag_apelido;

    @FXML
    private Button reg_estag_cancelar;

    @FXML
    private TextField reg_estag_celular;

    @FXML
    private PasswordField reg_estag_confirmar_palavra;

    @FXML
    private TextField reg_estag_curso;

    @FXML
    private DatePicker reg_estag_date;

    @FXML
    private TextField reg_estag_endereco;

    @FXML
    private TextField reg_estag_instituto;

    @FXML
    private TextField reg_estag_nome_usuario;

    @FXML
    private TextField reg_estag_pNome;

    @FXML
    private PasswordField reg_estag_palavra;

    @FXML
    private Button reg_estag_registar;

    @FXML
    private ComboBox<String> reg_estag_sexo;

    @FXML
    private ComboBox<?> reg_estag_status;

    @FXML
    private TextField reg_med_BI;

    @FXML
    private TextField reg_med_Espec;

    @FXML
    private TextField reg_med_apelido;

    @FXML
    private Button reg_med_cancelar;

    @FXML
    private TextField reg_med_celular;

    @FXML
    private PasswordField reg_med_confirmar_palavra;

    @FXML
    private DatePicker reg_med_datePicker;

    @FXML
    private TextField reg_med_email;

    @FXML
    private TextField reg_med_endereco;

    @FXML
    private ComboBox<String> reg_med_genero;

    @FXML
    private TextField reg_med_nome_usuario;

    @FXML
    private TextField reg_med_pNome;

    @FXML
    private PasswordField reg_med_palavra_passe;

    @FXML
    private Button reg_med_registar;

    @FXML
    private ComboBox<?> reg_med_status;

    @FXML
    private TextField reg_tecnico_BI;

    @FXML
    private TextField reg_tecnico_apelido;

    @FXML
    private Button reg_tecnico_cancelar;

    @FXML
    private TextField reg_tecnico_celular;

    @FXML
    private PasswordField reg_tecnico_confirmar_palavra;

    @FXML
    private DatePicker reg_tecnico_date;

    @FXML
    private TextField reg_tecnico_email;

    @FXML
    private TextField reg_tecnico_ender;

    @FXML
    private TextField reg_tecnico_espec;

    @FXML
    private ComboBox<String> reg_tecnico_genero;

    @FXML
    private TextField reg_tecnico_nome_usuario;

    @FXML
    private TextField reg_tecnico_pNome;

    @FXML
    private PasswordField reg_tecnico_palavra;

    @FXML
    private Button reg_tecnico_registar;

    @FXML
    private ComboBox<?> reg_tecnico_status;

    @FXML
    private ComboBox<String> registar_escolha;

    @FXML
    private AnchorPane registar_estagiario;

    @FXML
    private AnchorPane registar_medicos;

    @FXML
    private AnchorPane registar_tecnico;

    @FXML
    private Button relatorio_lista_doencas;

    @FXML
    private Button relatorio_lista_estagiario;

    @FXML
    private Button relatorio_lista_medico;

    @FXML
    private Button relatorio_lista_pa_altas;

    @FXML
    private Button relatorio_lista_pa_curado;

    @FXML
    private Button relatorio_lista_pa_falecido;

    @FXML
    private Button relatorio_lista_pa_inte;

    @FXML
    private Button relatorio_lista_paciente;

    @FXML
    private Button relatorio_lista_tecnico;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTimeUpdater();
        setName();
        admin_form_text.setText("Dashboard");
        configurarTabelaMedicos();
        configurarTabelaAltas();
        addRegistar();
        setComboSexo();

        Platform.runLater(() -> {
            admin_dashboard.getStyleClass().add("selected");
            selectedButton = admin_dashboard;
        });
    }

    private void addRegistar() {
        ObservableList<String> list = FXCollections.observableArrayList(Users.registar);
        registar_escolha.setItems(list);
        registar_escolha.getSelectionModel().selectFirst();
    }

    public void selectRegister() {
        switch (registar_escolha.getSelectionModel().getSelectedItem()) {
            case "Médico":
                showRegistarMedico();
                break;

            case "Tecnico":
                showRegistarTecnico();
                break;

            case "Estagiário":
                showRegistarEstagiario();
                break;

        }
    }

    private void initTimeUpdater() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            String formattedTime = new SimpleDateFormat(DATE_FORMAT).format(new Date());
            Platform.runLater(() -> admin_date.setText(formattedTime));
        }, 0, TIME_UPDATE_DELAY, TimeUnit.SECONDS);
    }

    private void shutdownScheduler() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void sair() {
        shutdownScheduler();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginRegister-view.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Alpha3 Login");
            stage.setResizable(false);
            stage.show();

            admin_main.getScene().getWindow().hide();
        } catch (IOException e) {
            alertMessage.errorMessage("Erro ao carregar a tela de login");
        }
    }

    private void configurarTabelaMedicos() {
        admin_med_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        admin_med_table.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 0) {
                admin_med_table_pNome.setPrefWidth(newVal.doubleValue() * 0.08);
                admin_med_table_apelido.setPrefWidth(newVal.doubleValue() * 0.15);
                admin_med_table_Sexo.setPrefWidth(newVal.doubleValue() * 0.08);
                admin_med_table_Celular.setPrefWidth(newVal.doubleValue() * 0.12);
                admin_med_table_emal.setPrefWidth(newVal.doubleValue() * 0.20);
                admin_med_table_Espc.setPrefWidth(newVal.doubleValue() * 0.17);
                admin_med_table_End.setPrefWidth(newVal.doubleValue() * 0.15);
                admin_med_table_Idade.setPrefWidth(newVal.doubleValue() * 0.05);
            }
        });
    }

    private void configurarTabelaAltas() {
        admin_alta_semanal.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        admin_alta_semanal.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 0) {
                admin_alta_semanal_nomePa.setPrefWidth(newVal.doubleValue() * 0.30);
                admin_alta_semanal_doenca.setPrefWidth(newVal.doubleValue() * 0.25);
                admin_alta_semanal_medico.setPrefWidth(newVal.doubleValue() * 0.25);
                admin_alta_semanal_sexo.setPrefWidth(newVal.doubleValue() * 0.10);
                admin_alta_semanal_quarto.setPrefWidth(newVal.doubleValue() * 0.10);
            }
        });
    }

    private void setName() { admin_user.setText(HospitalController.getUser_name()); }

    private void handleButtonSelection(Button clickedButton) {
        // Se o botão clicado já está selecionado, não faz nada
        if (selectedButton == clickedButton) {
            return;
        }

        // Remove a classe 'selected' do botão anterior
        if (selectedButton != null) {
            selectedButton.getStyleClass().remove("selected");
        }

        // Adiciona a classe 'selected' ao novo botão
        clickedButton.getStyleClass().add("selected");
        selectedButton = clickedButton;
    }

    public void swapMainForm(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        handleButtonSelection(clickedButton);

        if (clickedButton == admin_dashboard) {
            showDashboard();
        } else if (clickedButton == admin_medicos) {
            showMedicos();
        } else if (clickedButton == admin_pacientes) {
            showPacientes();
        } else if (clickedButton == admin_estagiario) {
            showEstagiario();
        } else if (clickedButton == admin_tecnicos) {
            showTecnico();
        } else if (clickedButton == admin_registar) {
            showRegistar();
        } else if (clickedButton == admin_relatorio) {
            showRelatorios();
        } else if (clickedButton == admin_conta) {
            showConta();
        }
        // Adicione outros botões conforme necessário
    }

    private void showDashboard() {
        admin_dashboard_form.setVisible(true);
        admin_medicos_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_form.setVisible(false);
        admin_conta_form.setVisible(false);
        admin_form_text.setText("Dashboard");
    }

    private void showMedicos() {
        admin_medicos_form.setVisible(true);
        admin_dashboard_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_form.setVisible(false);
        admin_conta_form.setVisible(false);
        admin_form_text.setText("Médicos");
    }

    private void showPacientes() {
        admin_pacientes_form.setVisible(true);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_form.setVisible(false);
        admin_conta_form.setVisible(false);
        admin_form_text.setText("Pacientes");
    }

    private void showEstagiario() {
        admin_estagiario_form.setVisible(true);
        admin_pacientes_form.setVisible(false);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_form.setVisible(false);
        admin_conta_form.setVisible(false);
        admin_form_text.setText("Estagiários");
    }

    private void showTecnico() {
        admin_tecnicos_form.setVisible(true);
        admin_estagiario_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_form.setVisible(false);
        admin_conta_form.setVisible(false);
        admin_form_text.setText("Tecnico");
    }

    private void showRegistar() {
        admin_registar_form.setVisible(true);
        admin_relatorios_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_conta_form.setVisible(false);
        admin_form_text.setText("Registar");
    }

    private void showRelatorios() {
        admin_relatorios_form.setVisible(true);
        admin_registar_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_conta_form.setVisible(false);
        admin_form_text.setText("Relatórios");
    }

    private void showConta() {
        admin_conta_form.setVisible(true);
        admin_dashboard_form.setVisible(false);
        admin_medicos_form.setVisible(false);
        admin_tecnicos_form.setVisible(false);
        admin_pacientes_form.setVisible(false);
        admin_estagiario_form.setVisible(false);
        admin_registar_form.setVisible(false);
        admin_relatorios_form.setVisible(false);
        admin_form_text.setText("Conta");
    }

    private void showRegistarMedico() {
        registar_medicos.setVisible(true);
        registar_estagiario.setVisible(false);
        registar_tecnico.setVisible(false);
    }

    private void showRegistarTecnico() {
        registar_tecnico.setVisible(true);
        registar_medicos.setVisible(false);
        registar_estagiario.setVisible(false);
    }

    private void showRegistarEstagiario() {
        registar_estagiario.setVisible(true);
        registar_medicos.setVisible(false);
        registar_tecnico.setVisible(false);
    }

    private void setComboSexo(){
        ObservableList<String> list = FXCollections.observableArrayList(Users.sexo);
        reg_estag_sexo.setItems(list);
        reg_med_genero.setItems(list);
        reg_tecnico_genero.setItems(list);
    }

    public void setPhoto(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Image File", "*.png"));
        File selectedFile = fc.showOpenDialog(null);
        try {
            editar_image.setImage(new Image(selectedFile.toURL().openStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}