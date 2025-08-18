package delivery.service.main.response;

import java.util.List;

public class DeliveryCostResponse {
	private List<OutputDetails> outputList;

	public List<OutputDetails> getOutputList() {
		return outputList;
	}

	public void setOutputList(List<OutputDetails> outputList) {
		this.outputList = outputList;
	}

	@Override
	public String toString() {
		return "DeliveryCostResponse [outputList=" + outputList + "]";
	}

}
