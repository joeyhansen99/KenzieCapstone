import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import CardClient from "../api/cardClient";

class CollectionPage extends BaseClass {

    constructor() {
        console.log("CONSTRUCTOR");
        super();
        this.bindClassMethods(['onGetTable'], this);
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

        console.log(cardTableData);

        if (cardTableData.length > 0) {
            $('#collectionDataNo').hide();
            //console.log('cardTableData = ' + cardTableData);
            var html = '';
            for(var i = 0; i < cardTableData.length; i++) {
                    html += `<tr>`;
                    html += `<td>` + cardTableData[i].set + `</td>`
                    html += `<td>` + cardTableData[i].name + `</td>`
                    html += `<td>` + cardTableData[i].cost + `</td>`
                    html += `<td>` + cardTableData[i].type + `</td>`
                    html += `<td>` + cardTableData[i].color + `</td>`
                    html += `<td>` + cardTableData[i].rarity + `</td>`
                    html += `<td>` + cardTableData[i].foil + `</td>`
                    html += `<td>` + cardTableData[i].fullArt + `</td>`
                    html += `<td>` + cardTableData[i].quantity + `</td>`
                    html += `<td>
                        <button type="button" class="editCard" onclick="editCard"><i class="fas fa-edit"></i>  Edit</button>
                        <button type="button" class="deleteCard" onclick="deleteCard"><i class="fas fa-trash"></i>  Delete</button>
                    </td>`
                    html += `</tr>`;
            }
            let cardCollectionTable = document.getElementById('collectionTable').innerHTML=html;
        } else {
            console.log("dataNoShow");
            $('#collectionDataNo').show();
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