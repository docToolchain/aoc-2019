class ChemicalSet {

    count: any = {};


    add(cnt: number, name: string) {
        if(!(name in this.count))
            this.count[name] = 0;
        this.count[name] += cnt;
    }

    delete(cnt: any, name: string) {
        if(cnt == 0) return;
        this.count[name] -= cnt;
        if(this.count[name] == 0)
            delete this.count[name]
    }

    getCnt(name: string) : number {

        if(!(name in this.count))
            return 0;
        return this.count[name];

    }

}
export { ChemicalSet }