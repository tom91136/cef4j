(() => {
  const run = new URLSearchParams(location.search).get("run") || "missing";
  const state = window.fixtureState = {
    run,
    clicks: 0,
    typed: "",
    clickResponse: null,
    continuedResponse: null,
    mockResponse: null
  };

  const input = document.querySelector("#name-input");
  input.addEventListener("input", () => {
    state.typed = input.value;
    document.querySelector("#typed-output").textContent = input.value || "empty";
  });

  document.querySelector("#action-button").addEventListener("click", async () => {
    state.clicks += 1;
    document.querySelector("#click-output").textContent = String(state.clicks);
    console.log("cef4j-fixture-click", state.clicks, run);
    const response = await fetch(`/cdp/api/click?run=${encodeURIComponent(run)}&count=${state.clicks}`);
    state.clickResponse = await response.json();
    document.querySelector("#fetch-output").textContent = state.clickResponse.message;
  });

  window.loadContinuedResource = async () => {
    const response = await fetch(`/cdp/api/continued?run=${encodeURIComponent(run)}`);
    state.continuedResponse = await response.json();
    document.querySelector("#continued-output").textContent = state.continuedResponse.source;
    return state.continuedResponse;
  };

  window.loadMockResource = async () => {
    const response = await fetch(`/cdp/api/mock?run=${encodeURIComponent(run)}`);
    state.mockResponse = await response.json();
    document.querySelector("#mock-output").textContent = state.mockResponse.source;
    return state.mockResponse;
  };

  window.delayedFixtureValue = () => Promise.resolve({answer: 42, run});
  window.throwFixtureError = () => { throw new Error(`cef4j-fixture-error-${run}`); };
  console.log("cef4j-fixture-ready", run);
})();
