var gulp = require('gulp');
var ts = require('gulp-typescript');
var mocha = require('gulp-mocha');
var tsProject = ts.createProject('tsconfig.json');

gulp.task('compile', function () {
    return tsProject.src()
        .pipe(tsProject())
        .js.pipe(gulp.dest('dist'));
});

gulp.task('test', function(){
    return gulp.src('test/*.spec.ts')
          .pipe(mocha({
              reporter: 'nyan',
              require: ['ts-node/register']
          }));
});


gulp.task('default', gulp.series(['compile' , 'test']));

