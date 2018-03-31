/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.listeners;

import algoritimos.dao.EntityManagerHelper;
import algoritimos.frames.PesquisaDefaultForm;
import algoritimos.listener.ListenerCBIAdapter;
import br.com.atenas.entidades.Email;
import br.com.atenas.entidades.Entidade;
import br.com.atenas.entidades.Telefone;
import br.com.atenas.frames.Cad_ClienteFrame;
import br.com.atenas.tablemodel.ClientePesquisaTableModel;
import br.com.atenas.tablemodel.EmailTableModel;
import br.com.atenas.tablemodel.TelefoneTableModel;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Tiago
 */
public final class Cad_ClienteListener extends ListenerCBIAdapter {

    private final Cad_ClienteFrame form;
    private final List<JPanel> paineis = new ArrayList();
    private Entidade cliente = new Entidade();
    private final EntityManagerHelper emh = new EntityManagerHelper();
    private EmailTableModel modelEmail;
    private TelefoneTableModel modelTelefone;

    public Cad_ClienteListener(Cad_ClienteFrame form) {
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
        form.getPainel_Telefones().getBtAdicionar().setActionCommand("adicionarTel");
        form.getPainel_Telefones().getBtAdicionar().addActionListener(this);
        form.getPainel_Telefones().getBtRemover().setActionCommand("removerTel");
        form.getPainel_Telefones().getBtRemover().addActionListener(this);
        form.getPainel_Emails().getBtAdicionar().setActionCommand("adicionarEmail");
        form.getPainel_Emails().getBtAdicionar().addActionListener(this);
        form.getPainel_Emails().getBtRemover().setActionCommand("removerEmail");
        form.getPainel_Emails().getBtRemover().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        switch (e.getActionCommand()) {
            case "novo":
                novo(form.getTxtId(), paineis, form.getMenuBarCbiDefault().getListMenuItens());
                break;
            case "cancelar":
                cancelar(paineis, form.getMenuBarCbiDefault().getListMenuItens());
                break;
            case "salvar":
                cliente.clear();
                salvar(0, emh, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU, form, paineis, form.getMenuBarCbiDefault().getListMenuItens(), cliente);
                break;
            case "alterar":
                salvar(1, emh, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU, form, paineis, form.getMenuBarCbiDefault().getListMenuItens(), cliente);
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
            case "adicionarTel":
                modelTelefone.addObject(new Telefone());
                break;
            case "removerTel":
                if (form.getTbEmails().getSelectedRow() > -1) {
                    JOptionPane.showMessageDialog(form, "Selecione um registro antes de tentar remove-lo", "Nenhum registro selecionado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    modelTelefone.removeObject(form.getTbTelefones().getSelectedRow());
                }
                break;
            case "adicionarEmail":
                modelEmail.addObject(new Email());
                break;
            case "removerEmail":
                if (form.getTbEmails().getSelectedRow() > -1) {
                    JOptionPane.showMessageDialog(form, "Selecione um registro antes de tentar remove-lo", "Nenhum registro selecionado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    modelTelefone.removeObject(form.getTbEmails().getSelectedRow());
                }
                break;
        }
        form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void carregarPaineis() {
        paineis.add(form.getPainelMain());
        paineis.add(form.getPainel_Telefones());
        paineis.add(form.getPainel_Emails());
    }

    @Override
    public void pesquisar() {
        PesquisaDefaultForm pesq = pesquisar("Pesquisa de Clientes",
                new ClientePesquisaTableModel(emh.getObjectListNamedQuery(Entidade.class, "entidade.findAll", null, null, EntityManagerHelper.PERSISTENCE_UNIT.DERBYDB_PU)),
                null, this, 50, 250, 150);
        pesq.setVisible(true);
    }

    @Override
    public void setDados(Object object) {
        cliente.clonar((Entidade) object);
    }

    @Override
    public void setDados() {
        super.setDados(form, cliente);
    }

    @Override
    public void getDados() {
        super.getDados(form, cliente);
    }

    @Override
    public void addModel() {
        modelEmail = new EmailTableModel();
        modelTelefone = new TelefoneTableModel();
        form.getPainel_Emails().getTbDados().setModel(modelEmail);
        form.getPainel_Telefones().getTbDados().setModel(modelTelefone);
    }

}
