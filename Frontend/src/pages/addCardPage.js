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

            let type = Array.from(document.querySelectorAll('cardType:checked'));
            let color = Array.from(document.querySelectorAll('cardColor:checked'));

            let rarity = document.getElementById("addCardRarity").value;
            if (document.getElementById('foilTrue').checked) {
                let foil = true;
            }
            if (document.getElementById('fullArtTrue').checked) {
                let fullArt = true;
            }

            let quantity = document.getElementById("cardQuantityInput").value;
            let id = quantity + cost + "-" + set + "-EN";

            console.log(id);
            console.log(set);
            console.log(name);
            console.log(cost);
            console.log(cardType);
            console.log(cardColor);
            console.log(rarity);
            console.log(foil);
            console.log(fullArt);
            console.log(quantity);
            console.log(type);
            console.log(color);


            const createdEvent = await this.cardClient.createCard(id,name,set,foil,fullArt,quantity,
                cost,color,type,rarity);

            // this.dataStore.set("createdEvent",createdEvent);

            if (createdEvent) {
                this.showMessage('Card was added to your collection.')
//                this.showMessage(`Created ${createdCard.id}!`)
            } else {
                this.errorHandler("Error creating! Try again...")
            }

        }

        async renderExample() {
    //        let resultArea = document.getElementById("result-info");
    //
    //        const example = this.dataStore.get("example");
    //
    //        if (example) {
    //            resultArea.innerHTML = `
    //                <div>ID: ${example.id}</div>
    //                <div>Name: ${example.name}</div>
    //            `
    //        } else {
    //            resultArea.innerHTML = "No Item";
    //        }
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