package uk.gav.sp.struct.b;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.struct.b.Bridge.AnniversaryShapePacker;
import uk.gav.sp.struct.b.Bridge.BasicPackage;
import uk.gav.sp.struct.b.Bridge.BirthdayShapePacker;
import uk.gav.sp.struct.b.Bridge.Colour;
import uk.gav.sp.struct.b.Bridge.Cone;
import uk.gav.sp.struct.b.Bridge.Cuboid;
import uk.gav.sp.struct.b.Bridge.PremierPackage;
import uk.gav.sp.struct.b.Bridge.ShapePacker;
import uk.gav.sp.struct.b.Bridge.Sphere;

/**
 * 
 * @author regen Create a 'bridge' between Shape Packers and the actual packages
 *         to pack. This prevents the potential for hierarchical overload. If
 *         inheritance were to be used instead then every new package type,
 *         would potentially create three or more sub-classes. This separation
 *         also allows packages to be maintained independently of the packer
 *         structure
 */
public class BClient extends ClientApplication {

	public static void main(String[] args) throws Exception {
		ClientApplication.run(BClient.class);
	}

	/*
	 * Perform the client code to provide two packs based on different package type
	 * and then defer to a specific client to build up the packages using the
	 * abstraction ShapePacker only.
	 */
	@Override
	public void clientCode() throws Exception {
		ShapePacker sp = new BirthdayShapePacker(new BasicPackage());
		this.clientCodeBirthday(sp);

		sp = new AnniversaryShapePacker(new PremierPackage());
		this.clientCodeAnniversary(sp);
	}

	public void clientCodeBirthday(final ShapePacker packer) {
		System.out.println("Creating our birthday pressie");
		packer.addShape(new Cuboid(5, 4, 3, Colour.BLUE));
		packer.addShape(new Cuboid(2, 2, 2, Colour.GOLD));
		System.out.println("Cost with just my cubes:" + packer.getCost());
		packer.addShape(new Cone(3, 10, Colour.TRANSPARENT));
		packer.addShape(new Sphere(3, Colour.TRANSPARENT));
		packer.packAndSend();
	}

	public void clientCodeAnniversary(final ShapePacker packer) {
		System.out.println("Creating our anniversary pressie");
		packer.addShape(new Cuboid(5, 4, 3, Colour.GOLD));
		packer.addShape(new Cuboid(2, 2, 2, Colour.GOLD));
		System.out.println("Cost with just my cubes:" + packer.getCost());
		packer.addShape(new Cone(3, 10, Colour.RED));
		packer.addShape(new Sphere(3, Colour.RED));
		packer.packAndSend();
	}
}
