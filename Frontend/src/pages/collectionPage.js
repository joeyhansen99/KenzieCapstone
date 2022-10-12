import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import CardClient from "../api/cardClient";

class CollectionPage extends BaseClass {

    constructor() {
        console.log("CONSTRUCTOR");
        super();
        this.bindClassMethods(['onGetTable'], this);
//        this.cardDataStore = new DataStore();
    }

    async mount() {
        console.log("Hit the mount");
//        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
//        document.getElementById('create-form').addEventListener('submit', this.onCreate);
//        document.getElementByClass('editCard').addEventListener('click', this.onEditCardButton);
        this.client = new CardClient();
        this.onGetTable();
        //this.cardDataStore.addChangeListener(this.renderTable);
    }

    async onGetTable() {
        console.log("onGetTableHit");
        let cardTableData = await this.client.getAllCards(this.errorHandler);
//        console.log(cardTableData)
//        if (cardTableData.length > 0) {
//            $('#collectionDataNo').hide();
//            console.log('cardTableData = ' + cardTableData)
//        } else {
//            console.log("dataNoShow");
//            $('#collectionDataNo').show();
//        }
    }

    // Render Methods --------------------------------------------------------------------------------------------------
    //displays the information
    async renderTable() {
//        let cardCollectionTable = document.getElementById('collectionTable').innerHTML;
//        let cardTableData = await this.cardClient.getAllCards(this.errorHandler);
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    console.log("made it to run method");
    const collectionPage = new CollectionPage();
    console.log("about to hit mount");
    await collectionPage.mount();
};

window.addEventListener('DOMContentLoaded', main);