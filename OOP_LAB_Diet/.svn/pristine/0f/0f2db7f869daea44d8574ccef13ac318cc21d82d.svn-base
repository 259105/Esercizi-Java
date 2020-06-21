package diet;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	private String name;
	private Food foods;
	private Map<NutritionalElement,Double> recipes=new LinkedHashMap<>();
	private Set<NutritionalElement> products=new LinkedHashSet<>();

	public Menu(String name,Food foods) {
		this.name = name;
		this.foods=foods;
	}
	/**
	 * Adds a given serving size of a recipe.
	 * 
	 * The recipe is a name of a recipe defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
	public Menu addRecipe(String recipe, double quantity) {
		NutritionalElement r =foods.getRecipe(recipe);
		recipes.put(r, quantity);
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
		NutritionalElement p=foods.getProduct(product);
		products.add(p);
		return this;
	}

	/**
	 * Name of the menu
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		double sumr=0,g=0,sump=0;
		Set<NutritionalElement> nes=recipes.keySet();
		for(NutritionalElement ne: nes) {
			double qta=recipes.get(ne);
			g+=qta;
			sumr+=ne.getCalories()*qta/100;
		}
		for(NutritionalElement ne:products)
			sump+=ne.getCalories();
		return sumr+sump;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		double sumr=0,g=0,sump=0;
		Set<NutritionalElement> nes=recipes.keySet();
		for(NutritionalElement ne: nes) {
			double qta=recipes.get(ne);
			g+=qta;
			sumr+=ne.getProteins()*qta/100;
		}
		for(NutritionalElement ne:products)
			sump+=ne.getProteins();
		return sumr+sump;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		double sumr=0,g=0,sump=0;
		Set<NutritionalElement> nes=recipes.keySet();
		for(NutritionalElement ne: nes) {
			double qta=recipes.get(ne);
			g+=qta;
			sumr+=ne.getCarbs()*qta/100;
		}
		for(NutritionalElement ne:products)
			sump+=ne.getCarbs();
		return sumr+sump;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		double sumr=0,g=0,sump=0;
		Set<NutritionalElement> nes=recipes.keySet();
		for(NutritionalElement ne: nes) {
			double qta=recipes.get(ne);
			g+=qta;
			sumr+=ne.getFat()*qta/100;
		}
		for(NutritionalElement ne:products)
			sump+=ne.getFat();
		return sumr+sump;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean 	indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
}
