var fs = require('fs');
import { Modul } from "./Modul";
export class Spacecraft {
    modules: Array<Modul> = [];

    addModul(modul: Modul) {
        this.modules.push(modul);
    }

    getModules() : Array<Modul> {
        return this.modules;
    }

    loadModules(textfile: string) {
        let data = fs.readFileSync(textfile).toString('utf-8');
        let modulMasses = data.split("\n");
        for (var modulMass of modulMasses) {
            this.addModul(new Modul(parseInt(modulMass)));
        }
    }

}
