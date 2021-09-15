/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.InsufficientFundsException;
import dao.NoItemInventoryException;
import dao.VendingMachinePersistenceException;
import dto.Product;
import service.VendingMachineServiceLayer;
import ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author junha
 */
public class VendingMachineController {
    private final VendingMachineView view;
    private final VendingMachineServiceLayer serviceLayer;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer serviceLayer) {
        this.view = view;
        this.serviceLayer = serviceLayer;
    }

    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                listMenu();
                menuSelection = getFirstSelection();

                switch (menuSelection) {
                    case 1 -> {
                        addCash();
                        run2();
                    }
                    case 2 -> keepGoing = false;
                    default -> unknownCommand();
                }
            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitMessage();
    }

    private void listMenu() throws VendingMachinePersistenceException {
        List<Product> products = serviceLayer.listItems();
        view.displayProductList(products);
    }

    private int getFirstSelection() {
        return view.displayInitialOptions(); //Display "Press 1 to add cash or press 2 to exit"
    }

    private void addCash() {
        // use view.getCash() to input cash from console
        serviceLayer.setCurrentCash(serviceLayer.getCurrentCash().add(view.getCash()));
    }

    private int getSecondSelection() throws VendingMachinePersistenceException {
        return view.getProductChoice();
    }

    private void run2() throws VendingMachinePersistenceException {
        Product userChoice = null;
        boolean keepGoing2 = false;
        do {
            listMenu();
            try {
                userChoice = getItem();
                keepGoing2 = false;
            } catch (InsufficientFundsException e) {
                view.displayErrorMessage(e.getMessage());
                keepGoing2 = true;
            }

        } while (keepGoing2);
        BigDecimal difference = serviceLayer.calculateChanges(serviceLayer.getCurrentCash(), userChoice.getProductId());
        view.dispenseChange(difference);
        serviceLayer.updateProduct(userChoice.getProductId());
    }

    private Product getItem() throws VendingMachinePersistenceException, InsufficientFundsException {
        Product productSelection = null;
        boolean keepGoing3 = true;
        do {
            int itemSelection = getSecondSelection();
            if (itemSelection == 0) {
                addCash();
            } else {
                try {
                    productSelection = serviceLayer.getItem(itemSelection); //get a product
                    keepGoing3 = false;
                } catch (NoItemInventoryException e) {
                    view.displayErrorMessage(e.getMessage());
                }
            }
        } while (keepGoing3);
        return productSelection;
    }
}
