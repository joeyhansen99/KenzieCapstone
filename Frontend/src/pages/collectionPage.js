import BaseClass from "../util/baseClass";
import CardClient from "../api/cardClient";

class CollectionPage extends BaseClass {

    constructor() {
        console.log("CONSTRUCTOR");
        super();
        this.bindClassMethods(['onGetTable', 'renderTable', 'onDeleteCard', 'onEditCard', 'onDeleteAllCards'], this);
    }

    async mount() {
        event.preventDefault();
        console.log("Hit the mount");
        this.client = new CardClient();
        this.onGetTable();
        document.getElementById('delete-card-form').addEventListener('submit', this.onDeleteCard);
        document.getElementById('edit-card-form').addEventListener('submit', this.onEditCard);
        document.getElementById('delete-all-cards-form').addEventListener('submit', this.onDeleteAllCards);
    }

    async onGetTable() {
        event.preventDefault();
        console.log("onGetTableHit");
        let cardTableData = await this.client.getAllCards(this.errorHandler);
        this.renderTable(cardTableData);
    }

    async onDeleteCard(event, cardTableData) {
        event.preventDefault();
        let deleteCardSelection = document.getElementById("deleteInput").value;
        console.log("delete card selection = " + deleteCardSelection);
        cardTableData = await this.client.getAllCards();
        console.log("card table data = " + cardTableData);
        console.log("card table data length = " + cardTableData.length);
        if (deleteCardSelection <= cardTableData.length) {
            await this.client.deleteCard(cardTableData[deleteCardSelection - 1].id);
            cardDeletedAlert(deleteCardSelection);
            location.reload();
            console.log("delete card function successful");
        } else {
            console.log("delete card function failed");
        }
    }

    async onEditCard(event, cardTableData) {
        event.preventDefault();
        let editCardSelection = document.getElementById("editInput").value;
        console.log("updating row " + editCardSelection);
        cardTableData = await this.client.getAllCards();
        console.log("CARD UPDATE ATTRIBUTES");
        console.log(cardTableData[editCardSelection - 1].id);
        console.log(document.getElementById("editFoilSelect").value);
        console.log(document.getElementById("editFullArtSelect").value);
        console.log(document.getElementById("quantityInput").value);
        console.log("get card = " + cardTableData[editCardSelection - 1]);
        if (editCardSelection <= cardTableData.length) {
            await this.client.updateCard(cardTableData[editCardSelection - 1].id,
                document.getElementById("editFoilSelect").value,
                document.getElementById("editFullArtSelect").value,
                document.getElementById("quantityInput").value);
            cardUpdatedAlert(editCardSelection);
            location.reload();
            console.log("edit card function successful");
        } else {
            console.log("edit card function failed");
        }
    }

    async onDeleteAllCards(event, cardTableData) {
        event.preventDefault();
        if (confirm("This will delete ALL cards in the collection. Are you sure?") == true) {
            cardTableData = await this.client.getAllCards();
            for (let i = 0; i < cardTableData.length; i++) {
                await this.client.deleteCard(cardTableData[i].id);
            }
            allCardsDeletedAlert();
            location.reload();
        }
    }

    async renderTable(cardTableData) {
        event.preventDefault();
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

const main = async () => {
    const collectionPage = new CollectionPage();
    console.log("about to hit mount");
    await collectionPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
