package gov.uspto.patent.doc.pap.items;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Node;

import gov.uspto.parser.dom4j.ItemReader;
import gov.uspto.patent.model.Figure;

public class DescriptionFigures extends ItemReader<List<Figure>> {

	public DescriptionFigures(Node itemNode) {
		super(itemNode);
	}

	@Override
	public List<Figure> read() {
		List<Figure> figures = new ArrayList<Figure>();

		@SuppressWarnings("unchecked")
		List<Node> paragraphNodes = itemNode.selectNodes("section/paragraph|paragraph");

		for (Node paragraphN : paragraphNodes) {
			@SuppressWarnings("unchecked")
			List<Node> figN = paragraphN.selectNodes("cross-reference");
			if (figN != null && !figN.isEmpty()) {
				String id = figN.get(0).getText();
				String text = paragraphN.getText().trim();
				Figure fig = new Figure(text, id);
				figures.add(fig);
			}
		}

		return figures;
	}
}
