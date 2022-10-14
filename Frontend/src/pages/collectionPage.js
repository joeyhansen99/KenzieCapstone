import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import CardClient from "../api/cardClient";

class CollectionPage extends BaseClass {

    constructor() {
        console.log("CONSTRUCTOR");
        super();
        this.bindClassMethods(['onGetTable', 'renderTable'], this);
    }

    async mount() {
        console.log("Hit the mount");
        this.client = new CardClient();
        this.onGetTable();
        //this.cardDataStore.addChangeListener(this.renderTable);
    }

    async onGetTable() {
        console.log("onGetTableHit");
        let cardTableData = await this.client.getAllCards(this.errorHandler);

        console.log(cardTableData);

        await this.renderTable(cardTableData);
    }

    // Render Table Method ---------------------------------------------------------------------------------------------
    async renderTable(cardTableData) {
        let rows = cardTableData.length;
        if (rows > 0) {
            $(`#collectionTable`).show();
            $(`#collectionDataNo`).hide();
            console.log('cardTableData length = ' + cardTableData.length);
            let rowNum = 0;
            for(var i = 0; i < cardTableData.length; i++) {
                rowNum++;
                $(`#collectionDataYes`).append(`
                    <tr style="cursor: pointer;">
                    <td>${rowNum}</td>
                    <td>${cardTableData[i].set}</td>
                    <td>${cardTableData[i].name}</td>
                    <td>${cardTableData[i].cost}</td>
                    <td>${cardTableData[i].type}</td>
                    <td>${cardTableData[i].color}</td>
                    <td>${cardTableData[i].rarity}</td>
                    <td>${cardTableData[i].foil}</td>
                    <td>${cardTableData[i].fullArt}</td>
                    <td>${cardTableData[i].quantity}</td>
                    </tr>
                `);
            }
        } else {
            console.log("dataNoShow");
            $(`#collectionDataNo`).show();
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
//    console.log("made it to run method");
    const collectionPage = new CollectionPage();
    console.log("about to hit mount");
    await collectionPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
