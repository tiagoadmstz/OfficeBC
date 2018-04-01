/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.listeners;

import algoritimos.dao.EntityManagerHelper;
import algoritimos.listener.ListenerCBIAdapter;
import algoritimos.util.OPERACAO;
import br.com.atenas.entidades.Entidade;
import br.com.atenas.entidades.Item;
import br.com.atenas.entidades.NotaCliente;
import br.com.atenas.entidades.Telefone;
import br.com.atenas.frames.Cad_NotaCliente;
import br.com.atenas.tablemodel.ItemTableModel;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Tiago
 */
public final class Cad_NotaClienteListener extends ListenerCBIAdapter {

    private final Cad_NotaCliente form;
    private ItemTableModel modelItens;
    private final NotaCliente nota = new NotaCliente();
    private final List<JPanel> paineis = new ArrayList();
    private final EntityManagerHelper emh = new EntityManagerHelper();

    public Cad_NotaClienteListener(Cad_NotaCliente form) {
        this.form = form;
        initComponents();
    }

    private void initComponents() {
        attachListener();
        carregarPaineis();
        addModel();
    }

    @Override
    public void attachListener() {
        form.getMenuBarCbiDefault().getListMenuItens().forEach(it -> {
            JMenuItem item = (JMenuItem) it;
            item.addActionListener(this);
        });
        fecharESC(form.getMenuBarCbiDefault().getItemFechar());
        form.getTxtDataEmissao().addFocusListener(this);
        form.getPainel_Cad_Geral().getBtAdicionar().setActionCommand("adicionarItem");
        form.getPainel_Cad_Geral().getBtAdicionar().addActionListener(this);
        form.getPainel_Cad_Geral().getBtRemover().setActionCommand("removerItem");
        form.getPainel_Cad_Geral().getBtRemover().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        switch (e.getActionCommand()) {
            case "novo":
                novo(null, paineis, form.getMenuBarCbiDefault().getListMenuItens());
                break;
            case "cancelar":
                cancelar(paineis, form.getMenuBarCbiDefault().getListMenuItens());
                break;
            case "salvar":
                prepararObjecto();
                salvar(0, emh, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU, form, paineis, null, nota);
                break;
            case "alterar":
                salvar(1, emh, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU, form, paineis, null, nota);
                break;
            case "editar":
                editar(paineis, form.getMenuBarCbiDefault().getListMenuItens());
                break;
            case "fechar":
                fechar(form);
                break;
            case "imprimir":
                break;
            case "excluir":
                break;
            case "pesquisar":
                pesquisar();
                break;
            case "adicionarItem":
                modelItens.addObject(new Item());
                break;
            case "removerItem":
                if(form.getTbItens().getSelectedRow() > 0){
                    modelItens.removeObject(form.getTbItens().getSelectedRow());
                } else {
                    
                }
                break;
        }
        form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void carregarPaineis() {
        paineis.add(form.getPainel_Main());
        paineis.add(form.getPainel_Cad_Geral());
    }

    @Override
    public void addModel() {
        modelItens = new ItemTableModel();
        form.getTbItens().setModel(modelItens);
    }

    @Override
    public void pesquisar() {

    }

    private void prepararObjecto() {
        Entidade ent = nota.getCliente();
        if (ent == null
                && !Objects.equals(null, form.getTxtDocumento().getText())
                && !Objects.equals(null, form.getTxtNome().getText())) {
            ent = new Entidade();
        }

        if (!Objects.equals(null, form.getTxtTelefone().getText()) && !Objects.equals("", form.getTxtTelefone().getText())) {
            ent.setTelefones(new ArrayList());
            ent.getTelefones().add(new Telefone());
        }
    }

    @Override
    public void setEnableButtons(OPERACAO codFunction) {
        setEnableButtons(codFunction, form.getMenuBarCbiDefault().getListMenuItens());
    }
}
