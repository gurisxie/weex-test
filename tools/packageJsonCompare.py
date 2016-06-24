import json
import argparse


class packageJsonCompare:
    def __init__(self, packagejson1, packagejson2):
        try:
            self.packageJson1 = json.load(file(packagejson1))
            self.packageJson2 = json.load(file(packagejson2))
        except Exception as IOError:
            print IOError
            exit(1)

            # print self.packageJson1
            # print self.packageJson2

    def isDevDependenciesSame(self):

        devDependencies1 = dict(self.packageJson1["devDependencies"])
        devDependencies2 = dict(self.packageJson2["devDependencies"])

        dependencies1 = dict(self.packageJson1["dependencies"])
        dependencies2 = dict(self.packageJson2["dependencies"])

        """

        """
        for (k, v) in devDependencies1.items():
            if devDependencies2.has_key(k) is False or devDependencies2[k] != v:
                return False

        for (k, v) in devDependencies2.items():
            if devDependencies1.has_key(k) is False or devDependencies1[k] != v:
                return False

        for (k, v) in dependencies1.items():
            if dependencies2.has_key(k) is False or dependencies2[k] != v:
                return False

        for (k, v) in dependencies2.items():
            if dependencies1.has_key(k) is False or dependencies1[k] != v:
                return False


        return True


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='process images....')
    parser.add_argument("-src1", type=str, help="one of the packagejson path")
    parser.add_argument("-src2", type=str, help="another packagejson path")

    args = parser.parse_args()

    c = packageJsonCompare(args.src1, args.src2)

    print c.isDevDependenciesSame()
