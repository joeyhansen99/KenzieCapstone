import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import CardClient from "../api/cardClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class CollectionPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['renderCards', 'onUpdateCard', 'onDeleteCard'], this);
        this.cardDataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        document.getElementByClass('editCard').addEventListener('click', this.onEditCardButton)
        this.cardClient = new CardClient();
        this.cardClient.getAllCards();
        this.cardDataStore.addChangeListener(this.renderExample);
    }

    // Render Methods --------------------------------------------------------------------------------------------------
    //displays the information
    async renderCards() {
        let cardCollectionTable = document.getElementById('collectionTable').innerHTML;
        let cardTableData = await this.cardClient.getAllCards(this.errorHandler);
//        let cardTableData = testData;
//        console.log('testData = ' + testData)
        this.cardDataStore.set('table', cardTableData);

        if (cardTableData.length > 0) {
            for (let card of tableData) {
                cardCollectionTable += '<tr>';
                cardCollectionTable += `<td>${card.set}</td>`;
                cardCollectionTable += `<td>${card.name}</td>`;
                cardCollectionTable += `<td>${card.cost}</td>`;
                cardCollectionTable += `<td>${card.type}</td>`;
                cardCollectionTable += `<td>${card.color}</td>`;
                cardCollectionTable += `<td>${card.rarity}</td>`;
                cardCollectionTable += `<td>${card.foil}</td>`;
                cardCollectionTable += `<td>${card.fullArt}</td>`;
                cardCollectionTable += `<td>${card.quantity}</td>`;
                cardCollectionTable +=
                    `<td>
                        <button type="button" class="editCard" onclick="editCard"><i class="fas fa-edit"></i>  Edit</button>
                        <button type="button" class="deleteCard" onclick="deleteCard"><i class="fas fa-trash"></i>  Delete</button>
                    </td>`;
                cardCollectionTable += '<tr>';
            }
        }

        document.getElementById('collectionTable').innerHTML = cardCollectionTable;

        //Example
        let resultArea = document.getElementById("result-info");

        const example = this.dataStore.get("example");

        if (example) {
            resultArea.innerHTML = `
                <div>ID: ${example.id}</div>
                <div>Name: ${example.name}</div>
            `
        } else {
            resultArea.innerHTML = "No Item";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

//    async onGet(event) {
//        // Prevent the page from refreshing on form submit
//        event.preventDefault();
//
//        let id = document.getElementById("id-field").value;
//        this.dataStore.set("example", null);
//
//        let result = await this.client.getExample(id, this.errorHandler);
//        this.dataStore.set("example", result);
//        if (result) {
//            this.showMessage(`Got ${result.name}!`)
//        } else {
//            this.errorHandler("Error doing GET!  Try again...");
//        }
//    }
//
//    async onCreate(event) {
//        // Prevent the page from refreshing on form submit
//        event.preventDefault();
//        this.dataStore.set("example", null);
//
//        let name = document.getElementById("create-name-field").value;
//
//        const createdExample = await this.client.createExample(name, this.errorHandler);
//        this.dataStore.set("example", createdExample);
//
//        if (createdExample) {
//            this.showMessage(`Created ${createdExample.name}!`)
//        } else {
//            this.errorHandler("Error creating!  Try again...");
//        }
//    }


}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const collectionPage = new CollectionPage();
    collectionPage.mount();
};

window.addEventListener('DOMContentLoaded', main);