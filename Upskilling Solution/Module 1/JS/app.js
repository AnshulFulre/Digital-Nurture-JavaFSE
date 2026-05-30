
console.log("[Portal Setup] Welcome to the Community Portal Engine.");

// Closures: Track registration increments dynamically
function createRegistrationTracker() {
    let totalRegistrations = 0; 
    return {
        increment: function() {
            totalRegistrations++;
            return totalRegistrations;
        },
        getCount: function() {
            return totalRegistrations;
        }
    };
}
const globalTracker = createRegistrationTracker();

// Objects, Classes, and Prototypes
class CommunityEvent {
    constructor(id, name, date, category, seats, fee = "Free") {
        this.id = id;
        this.name = name;
        this.date = new Date(date);
        this.category = category;
        this.seats = parseInt(seats);
        this.fee = fee;
    }

    // Prototype method
    checkAvailability() {
        return this.seats > 0 && this.date >= new Date("2026-01-01");
    }
}

// State Management
let masterEventRegistry = []; 
let filteredRegistry = [];

const MOCK_GET_URL = "https://jsonplaceholder.typicode.com/posts?_limit=4";
const MOCK_POST_URL = "https://jsonplaceholder.typicode.com/posts";

// ASYNCHRONOUS DATA FETCH ENGINE & FETCH API
async function fetchPortalEventsAsync() {
    const spinner = document.getElementById("loading-spinner");
    const container = document.getElementById("dynamic-event-grid");
    
    if (spinner) spinner.style.display = "block";
    container.innerHTML = ""; 

    try {
        const response = await fetch(MOCK_GET_URL);
        if (!response.ok) throw new Error(`HTTP Error Status: ${response.status}`);
        
        const rawNetworkData = await response.json();

        const categories = ["Tech", "Music", "Charity", "Music"];
        const dates = ["2026-06-15", "2026-07-22", "2026-08-05", "2026-05-20"];
        
        masterEventRegistry = rawNetworkData.map((item, index) => {
            return new CommunityEvent(
                item.id,
                `Community ${categories[index]} Session: ${item.title.substring(0, 15)}`,
                dates[index],
                categories[index],
                Math.floor(Math.random() * 12) + 1, 
                index === 1 ? "$50" : index === 0 ? "$150" : "Free"
            );
        });

        // Manual push demonstrating array mutation
        masterEventRegistry.push(new CommunityEvent(99, "Workshop on Baking Artisan Pastries", "2026-09-12", "Culinary", 15, "$25"));

        // Clone master array
        filteredRegistry = [...masterEventRegistry];
        renderEventPortalGrid(filteredRegistry);

    } catch (error) {
        container.innerHTML = `<div class="geo-box geo-error">⚠️ Failed to communicate with API Server endpoint: ${error.message}</div>`;
    } finally {
        if (spinner) spinner.style.display = "none";
    }
}

// DOM MANIPULATION, FILTERS, AND ITERATION
function renderEventPortalGrid(eventSourceList) {
    const container = document.querySelector("#dynamic-event-grid"); 
    container.innerHTML = "";

    if (eventSourceList.length === 0) {
        container.innerHTML = "<p style='grid-column: 1/-1; text-align:center; color:#64748b;'>No upcoming events match your parameters.</p>";
        return;
    }

    eventSourceList.forEach((eventItem) => {
        // Object Destructuring
        const { id, name, date, category, seats, fee } = eventItem;

        // Conditionals
        if (!eventItem.checkAvailability()) return; 

        // Generate DOM Elements
        const cardNode = document.createElement("div");
        cardNode.className = "portal-event-card";
        cardNode.id = `evt-card-${id}`;
        cardNode.setAttribute("data-category", category);

        const localizedDateStr = date.toLocaleDateString('en-US', { month: 'short', day: 'numeric', year: 'numeric' });
        
        // Template Literals
        cardNode.innerHTML = `
            <span class="category-tag">${category}</span>
            <h4 class="event-title-text">${name}</h4>
            <p><strong>Date:</strong> ${localizedDateStr}</p>
            <p><strong>Cost:</strong> <span class="highlight">${fee}</span></p>
            <p><strong>Seats Available:</strong> <span id="seat-count-${id}" class="seat-pill">${seats}</span></p>
            <button class="submit-btn" style="margin-top:10px;" onclick="handleDirectRegistration(${id})">⚡ Quick Register</button>
        `;

        container.appendChild(cardNode);
    });
}

// REAL-TIME EVENT FILTERS
function handleCategoryFiltering(selectValue) {
    if (!selectValue) {
        filteredRegistry = [...masterEventRegistry];
    } else {
        filteredRegistry = masterEventRegistry.filter(evt => evt.category.toLowerCase() === selectValue.toLowerCase());
    }
    renderEventPortalGrid(filteredRegistry);
}

function handleKeyboardSearch(searchQuery) {
    const sanitizedQuery = searchQuery.toLowerCase().trim();
    const matchedSet = filteredRegistry.filter(evt => evt.name.toLowerCase().includes(sanitizedQuery));
    renderEventPortalGrid(matchedSet);
}

// REGISTRATION LOGIC & ERROR HANDLING
function handleDirectRegistration(eventId) {
    const targetEvent = masterEventRegistry.find(evt => evt.id === eventId);
    
    try {
        if (!targetEvent) throw new Error("Target event lookup reference error.");
        if (targetEvent.seats <= 0) throw new Error("This community event has filled all available seats.");

        targetEvent.seats--;
        
        const countPill = document.getElementById(`seat-count-${eventId}`);
        if(countPill) {
            countPill.textContent = targetEvent.seats;
            if(targetEvent.seats === 0) countPill.style.backgroundColor = "#ef4444";
        }

        const absoluteRegCount = globalTracker.increment();
        alert(`Successfully registered for:\n${targetEvent.name}!\n\nPortal Transactions Handled: ${absoluteRegCount}`);

    } catch (error) {
        alert(`Registration Error: ${error.message}`);
    }
}

//FORM PROCESSING & BACKEND POST SIMULATION
async function processPortalFormSubmission(event) {
    event.preventDefault(); 
    
    const formElement = event.target;
    const attendeeName = formElement.elements["attendeeName"].value.trim();
    const attendeeEmail = formElement.elements["attendeeEmail"].value.trim();
    const rawTargetSelection = formElement.elements["formEventSelection"].value;

    const feedbackNotice = document.getElementById("form-inline-error");
    feedbackNotice.style.display = "none"; 

    if (!attendeeName || !attendeeEmail || !rawTargetSelection) {
        feedbackNotice.textContent = "Error: All registration form inputs require validation criteria clearance.";
        feedbackNotice.style.display = "block";
        return;
    }

    const payloadPayload = { name: attendeeName, email: attendeeEmail, targetEventString: rawTargetSelection, timestamp: new Date().toISOString() };

    const outBox = document.getElementById("form-confirmation");
    outBox.className = "form-output success-box";
    outBox.textContent = "⌛ Synchronizing secure registration parameters with remote servers...";
    outBox.classList.remove("hidden");

    try {
        const postResponse = await fetch(MOCK_POST_URL, {
            method: "POST",
            body: JSON.stringify(payloadPayload),
            headers: { "Content-type": "application/json; charset=UTF-8" }
        });

        if(!postResponse.ok) throw new Error("Remote cluster sync error occurred.");
        const networkConfirmReceipt = await postResponse.json();

        setTimeout(() => {
            outBox.innerHTML = `✅ <strong>Sync Confirmed!</strong> Server Node Assigned ID: RES-${networkConfirmReceipt.id}<br>Thank you, ${attendeeName}. Your reservation data has been safely recorded.`;
            formElement.reset();
        }, 1200);

    } catch (err) {
        outBox.className = "form-output geo-box geo-error";
        outBox.textContent = `❌ Network transmission layer failure: ${err.message}`;
    }
}

//GEOLOCATION HANDLING (From HTML5 Tasks)
function getUserLocation() {
    const displayBox = document.getElementById('geo-display');
    displayBox.className = "geo-box"; 
    displayBox.textContent = "🛰️ Querying satellites for device positioning data...";
    displayBox.classList.remove('hidden');

    if (!navigator.geolocation) {
        displayBox.textContent = "Geolocation is not supported by your client.";
        displayBox.classList.add('geo-error');
        return;
    }

    navigator.geolocation.getCurrentPosition(
        function(position) {
            const lat = position.coords.latitude.toFixed(5);
            const lon = position.coords.longitude.toFixed(5);
            displayBox.className = "geo-box";
            displayBox.innerHTML = `🎯 <strong>Success:</strong> Lat ${lat}°, Lon ${lon}°`;
        }, 
        function(error) {
            displayBox.classList.add('geo-error');
            if(error.code === error.PERMISSION_DENIED) displayBox.textContent = "⚠️ Permission Denied by user.";
            else if(error.code === error.TIMEOUT) displayBox.textContent = "⏳ Request timed out fetching position.";
            else displayBox.textContent = "Location discovery dropped.";
        }, 
        { enableHighAccuracy: true, timeout: 8000, maximumAge: 0 }
    );
}

//LEGACY DECOUPLING MODULE (JQUERY COMPATIBILITY LAYER)
function registerLegacyJQueryInterventions() {
    const fallbackActionButton = document.getElementById("legacy-sync-trigger");
    if(fallbackActionButton) {
        fallbackActionButton.onclick = function() {
            const wrapper = document.getElementById("dynamic-event-grid");
            wrapper.style.transition = "opacity 0.4s ease";
            wrapper.style.opacity = "0.1";
            
            setTimeout(() => {
                filteredRegistry = [...masterEventRegistry];
                document.getElementById("category-filter-select").value = "";
                document.getElementById("quick-search-input").value = "";
                renderEventPortalGrid(filteredRegistry);
                wrapper.style.opacity = "1";
            }, 400);
        };
    }
}

// Init
window.addEventListener("DOMContentLoaded", () => {
    fetchPortalEventsAsync();
    registerLegacyJQueryInterventions();
    
    setTimeout(() => {
        alert("Welcome to the Community Event Portal!\nAll real-time engine components are online.");
    }, 500);
});