import BaseClass from '../util/baseClass';
import DataStore from "../util/DataStore";
import CardClient from "../api/cardClient";


class AddCardPage extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['onCreateCard'], this);
//        this.cardDataStore = new DataStore();
    }


    async mount() {
        document.getElementById('addCardForm').addEventListener('submit', this.onCreateCard);
        this.cardClient = new CardClient();
    }

    // Event Handlers --------------------------------------------------------------------------------------------------
    async onCreateCard(event) {
            // Prevent the page from refreshing on form submit
            event.preventDefault();
            console.log("onCreateCard Hit")

            let set = document.getElementById("cardSetInput").value;
            let name = document.getElementById("cardNameInput").value;
            let cost = document.getElementById("cardCostInput").value;
            let foil = false;
            let fullArt = false;

            let cardType = document.getElementsByClassName("cardType").value;
            let cardColor = document.getElementsByClassName("cardColor").value;

            let color = document.querySelectorAll('[class=CardColor]:checked');
            var colorArray = []
            for (var i = 0; i < color.length; i++) {
                colorArray.push(color[i].value)
            }
            let type = document.querySelectorAll('[class=CardType]:checked');
            var typeArray = []
            for (var i = 0; i < type.length; i++) {
                typeArray.push(type[i].value)
            }

            let rarity = document.getElementById("addCardRarity").value;

            if (document.getElementById('foilTrue').checked) {
                foil = true;
            }
            if (document.getElementById('fullArtTrue').checked) {
                fullArt = true;
            }

            let quantity = document.getElementById("cardQuantityInput").value;
            let id = quantity + cost + "-" + set + "-EN";

            console.log(id);
            console.log(set);
            console.log(name);
            console.log(cost);
            console.log(rarity);
            console.log(foil);
            console.log(fullArt);
            console.log(quantity);
            console.log(typeArray);
            console.log(colorArray);

            const createdEvent = await this.cardClient.createCard(id,name,set,foil,fullArt,quantity,
                cost,colorArray,typeArray,rarity);

            // this.dataStore.set("createdEvent",createdEvent);

            if (createdEvent) {
//                this.showMessage('Card was added to your collection.')
//                this.showMessage(`Created ${createdCard.name}!`)
                this.showMessage(name + ' was added to your collection.')
            } else {
                this.errorHandler("Error creating! Try again...")
            }
        }

    handleError(method, error, errorCallback) {
        console.error(method + ' failed - ' + error);
        if(error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + ' failed - ' + error);
        }
    }
}

const main = async () => {
    console.log("made it to run method");
    const addCardPage = new AddCardPage();
    console.log("about to hit mount");
    addCardPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
