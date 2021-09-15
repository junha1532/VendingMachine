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

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {
            try {
                BigDecimal customerCash = new BigDecimal("0");
                listMenu();
                menuSelection = getFirstSelection();

                switch (menuSelection) {
                    case 1: {
                        customerCash = customerCash.add(addCash());
                        purchaseProduct(customerCash);
                        break;
                    }
                    case 2:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                        break;
                }
            } catch (VendingMachinePersistenceException e) {
                view.displayErrorMessage(e.getMessage());
            } catch (InsufficientFundsException exc) {
                view.displayErrorMessage(exc.getMessage());
            }
            exitMessage();
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
        return view.getSelection();
    }

    private BigDecimal addCash() {
        // use view.getCash() to input cash from console
        BigDecimal cash = view.getCash();
        return cash;
    }

    private int getSecondSelection() throws VendingMachinePersistenceException {
        return view.getProductChoice();
    }

    private void purchaseProduct(BigDecimal customerCash) throws VendingMachinePersistenceException, InsufficientFundsException {

        boolean keepGoing2 = false;
//        do {
//            listMenu();
//            try {
//                userChoice = getItem();
//                keepGoing2 = false;
//            } catch (InsufficientFundsException e) {
//                view.displayErrorMessage(e.getMessage());
//                keepGoing2 = true;
//            }
//
//        } while (keepGoing2);
        int productChoice = view.getProductChoice();
        BigDecimal change = serviceLayer.calculateChanges(customerCash, productChoice);
        view.dispenseChange(change);
        serviceLayer.updateProduct(productChoice);
        view.displaySuccessPruchase();
    }

    private Product getItem() throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        Product productSelection = null;
        boolean keepGoing3 = true;
        do {
            int itemSelection = getSecondSelection();
            if (itemSelection == 0) {
                addCash();
            } else {
                try {
                    productSelection = serviceLayer.getProduct(itemSelection); //get a product
                    keepGoing3 = false;
                } catch (NoItemInventoryException e) {
                    view.displayErrorMessage(e.getMessage());
                }
            }
        } while (keepGoing3);
        return productSelection;
    }
}
